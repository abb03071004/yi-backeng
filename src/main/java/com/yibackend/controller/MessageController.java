package com.yibackend.controller;


import com.yibackend.classes.dto.MessagePageDTO;
import com.yibackend.classes.entity.Message;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.result.PageResult;
import com.yibackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessage")
    public Result<PageResult> getMessage(MessagePageDTO messagePageDTO){
        PageResult res= messageService.getMessage(messagePageDTO);
         return Result.success(res);
    }

}
