package com.yibackend.classes.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewInfoVO {
    private Long userId;
    private String nickname;
}
