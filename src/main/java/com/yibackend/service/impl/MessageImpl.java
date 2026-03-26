package com.yibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yibackend.classes.dto.MessagePageDTO;
import com.yibackend.classes.entity.Message;
import com.yibackend.classes.result.PageResult;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.MessageMapper;
import com.yibackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageResult getMessage(MessagePageDTO messagePageDTO) {
        Page<Message> messagePage = new Page<>(messagePageDTO.getPageNum(), messagePageDTO.getPageSize());
        //TODO id改成动态的
        messageMapper.selectPage(messagePage,new LambdaQueryWrapper<Message>().eq(Message::getUserId, CurrentIdHolder.getCurrentId()).orderByAsc(Message::getCreatedTime));
        return new PageResult(messagePage.getTotal(),messagePage.getRecords());
    }
}
