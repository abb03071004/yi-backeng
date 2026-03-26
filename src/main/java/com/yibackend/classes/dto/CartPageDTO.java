package com.yibackend.classes.dto;

import lombok.Data;

@Data
public class CartPageDTO {
    private Integer pageNum;
    private Integer pageSize;
    private Integer status;
}
