package com.yibackend.classes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reviews {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private Long bookId;
    private Long cartItemId;
    private Integer rating;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

}
