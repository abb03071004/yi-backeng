package com.yibackend.controller;


import com.yibackend.classes.dto.SelfSalePageDTO;
import com.yibackend.classes.dto.UpdateBookStatus;
import com.yibackend.classes.entity.Books;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.BookOnGoodsListWithImageVO;
import com.yibackend.service.FindBooksService;
import com.yibackend.service.PostBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/selfInfo")
public class SelfInfoController {

    @Autowired
    private FindBooksService findBooks;

    @Autowired
    private PostBooksService postBooksService;


    @GetMapping("/getBooksByStatus")
    public Result<PageResult> getBooksByStatus(SelfSalePageDTO selfSalePageDTO) {
        PageResult res= findBooks.getBooksByStatus(selfSalePageDTO);
        return  Result.success(res);
    }

    @PostMapping("/updateBook")
    public Result updateBook(@RequestBody Books book){
        postBooksService.updateBook(book);
        return Result.success();
    }

    @PostMapping("/updateBookStatus")
    public Result updateBookStatus(@RequestBody UpdateBookStatus updateBookStatus){

        postBooksService.updateBookStatus(updateBookStatus);
        return Result.success();
    }
}
