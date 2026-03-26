package com.yibackend.classes.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginVO {
    private String accessToken;
    private String refreshToken;
}
