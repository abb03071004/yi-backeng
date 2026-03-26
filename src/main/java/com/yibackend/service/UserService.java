package com.yibackend.service;

import com.yibackend.classes.dto.RefreshDTO;
import com.yibackend.classes.dto.UserLoginDto;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.entity.Users;
import com.yibackend.classes.vo.UserLoginVO;

public interface UserService {
    Users selectById(Long i);

    UserLoginVO login(UserLoginDto userLoginDto);

    Result refreshToken(RefreshDTO refreshToken);
}
