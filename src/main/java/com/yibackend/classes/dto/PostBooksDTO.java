package com.yibackend.classes.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PostBooksDTO {
    private Long id;
    private String title;
    private String author;
    private Integer condition;
    private List<String> images;
    private Integer location;
    private BigDecimal price;
    private String notes;
    private String number;
    private String publisher;
    private Integer tradeMethod;
    private String version;

}
