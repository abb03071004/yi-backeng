package com.yibackend.classes.dto;

import lombok.Data;

@Data
public class BooksPageDTO {
    private String searchName;
    private Integer orderType;
    private Integer campus;
    private Integer pageNum;
    private Integer pageSize;
}
