package com.yibackend.controller;



import com.yibackend.classes.dto.BooksPageDTO;
import com.yibackend.classes.entity.Books;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.BookOnGoodsListWithImageVO;
import com.yibackend.service.FindBooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/findBooks")
@Slf4j
public class SearchController {

    @Autowired
    private FindBooksService findBooks;

    @GetMapping("/bookList")
    public Result<PageResult> findBooks(BooksPageDTO booksPageDTO) {
        PageResult res= findBooks.findBooksByPara(booksPageDTO);
        return Result.success(res);
    }
    @GetMapping("/getDetails")
    public Result<Books> getDetails(Integer id) {
        Books book= findBooks.getDetails(id);
        return Result.success(book);
    }

    @GetMapping("/getContactById")
    public Result<String> getContactById(Integer id) {
        String contact= findBooks.getContactById(id);
        return Result.success(contact);
    }

}
