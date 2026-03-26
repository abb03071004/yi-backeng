package com.yibackend.service;

import com.yibackend.classes.dto.MessagePageDTO;
import com.yibackend.classes.entity.Message;
import com.yibackend.classes.result.PageResult;

import java.util.List;

public interface MessageService {
    PageResult getMessage(MessagePageDTO messagePageDTO);
}
