package com.yibackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yibackend.classes.dto.RefreshDTO;
import com.yibackend.classes.dto.UserLoginDto;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.entity.Users;
import com.yibackend.classes.vo.UserLoginVO;
import com.yibackend.constant.JWTConstant;
import com.yibackend.constant.MessageConstant;
import com.yibackend.exception.LoginException;
import com.yibackend.mapper.UserMapper;
import com.yibackend.properties.JWTProperties;
import com.yibackend.properties.WeChatProperties;
import com.yibackend.service.UserService;
import com.yibackend.util.HttpClientUtil;
import com.yibackend.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserImpl implements UserService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private JWTProperties jwtProperties;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //api_address
    public static final String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";
    public static final String REFRESH_KEY_PRE="refresh_token:";


    @Override
    public Users selectById(Long i) {
        return userMapper.selectById(i);
    }

    @Override
    public UserLoginVO login(UserLoginDto userLoginDto) {
        String openid=getOpenid(userLoginDto.getCode());
        if(openid==null||openid.isEmpty()){
            throw new LoginException(MessageConstant.LOGIN_FAILED);
        }

        Users users = userMapper.selectOne(new LambdaQueryWrapper<Users>().eq(Users::getOpenid, openid));
        if(users==null){
            users= Users.builder()
                    .openid(openid)
                    //TODO 把路径换成服务器的对应路径
                    .avatar("https://localhost:8443/default-avatar.png")
                    .nickname("用户" + UUID.randomUUID().toString().substring(0, 8)).build();
            userMapper.insert(users);
        }
        users.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(users);
        Map<String,Object> map = new HashMap<>();
        map.put(JWTConstant.USER_ID,users.getId());
        //生成 accessToken
        String accessToken= JWTUtil.createJWT(jwtProperties.getSecretKey(),jwtProperties.getTtl(),map);

        // 生成 refreshToken
        String key = UUID.randomUUID().toString().replace("-", "");

        redisTemplate.opsForValue().set(REFRESH_KEY_PRE+key,users.getId().toString(),30L, TimeUnit.DAYS);


        return new UserLoginVO(accessToken,key);

    }

    @Override
    public Result refreshToken(RefreshDTO refreshToken) {
        Result res=new Result();
        String s = redisTemplate.opsForValue().getAndDelete(REFRESH_KEY_PRE + refreshToken.getRefreshToken());
        if(s==null||s.isEmpty()){
           res= new Result<>(401, MessageConstant.REFRESH_TOKEN_EXPIRED, null);
           return res;
        }
        //重新生成新的 refreshToken
        String newKey = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(REFRESH_KEY_PRE+newKey,s,30L, TimeUnit.DAYS);
        //生成新的 accessToken
        Map<String,Object> map = new HashMap<>();
        map.put(JWTConstant.USER_ID,s);
        //生成 accessToken
        String accessToken= JWTUtil.createJWT(jwtProperties.getSecretKey(),jwtProperties.getTtl(),map);
        res.setCode(200);
        res.setData(new UserLoginVO(accessToken,newKey));
        return res;
    }

    private String getOpenid(String code) {
        Map<String,String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        String response= HttpClientUtil.doGet(WX_LOGIN,map);
        return JSON.parseObject(response).getString("openid");

    }
}
