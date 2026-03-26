package com.yibackend.classes.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookOnGoodsListWithImageVO {
    private Long id;
    private String title;
    private String version;
    private BigDecimal price;
    private String publisher;
    private Integer condition;
    private List<String> image;
}
