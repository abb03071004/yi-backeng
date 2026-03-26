package com.yibackend.classes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Books {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String title;
    private String author;

    private String version;
    private BigDecimal price;
    private String publisher;
    private Integer condition;
    private Integer tradeMethod;
    private Integer location;
    private String notes;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private BigDecimal score;

    @TableField(exist = false)
    private List<String> image;



}
