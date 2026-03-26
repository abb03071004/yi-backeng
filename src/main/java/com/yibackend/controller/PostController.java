package com.yibackend.controller;


import com.yibackend.classes.dto.PostBooksDTO;
import com.yibackend.classes.entity.Result;
import com.yibackend.service.PostBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/postBooks")
public class PostController {

    @Autowired
    private PostBooksService postBooks;


    @PostMapping("/postBooks")
    public Result handlePostBooks(@RequestBody List<PostBooksDTO> books){
        postBooks.handlePostBooks(books);
        return Result.success();
    }


}
