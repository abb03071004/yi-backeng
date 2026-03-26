package com.yibackend.classes.vo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryVO extends BookOnGoodsListWithImageVO {
    private Long categoryId;
    private Integer status;

}
