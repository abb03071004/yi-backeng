package com.yibackend.controller;


import com.yibackend.classes.dto.RefreshDTO;
import com.yibackend.classes.dto.UserLoginDto;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.entity.Users;
import com.yibackend.classes.vo.UserLoginVO;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.UserMapper;
import com.yibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/selfInfo")
    public Result<Users> getMyInfo(){
        //TODO id改成动态的
        Users users = userService.selectById(CurrentIdHolder.getCurrentId());
        return Result.success(users);
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDto code){
        UserLoginVO userLoginVO = userService.login(code);
        return Result.success(userLoginVO);
    }

    @PostMapping("/refresh")
    public Result refresh(@RequestParam RefreshDTO refreshToken){
        Result accessToken= userService.refreshToken(refreshToken);
        return Result.success(accessToken);

    }

}
