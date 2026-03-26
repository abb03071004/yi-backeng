package com.yibackend.controller;

import com.yibackend.classes.dto.CartPageDTO;
import com.yibackend.classes.entity.Result;
import com.yibackend.classes.result.PageResult;
import com.yibackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategory")
    public Result<PageResult> getCategory(CartPageDTO cartPageDTO){
        PageResult res= categoryService.getCategory(cartPageDTO);
        return Result.success(res);
    }

    @DeleteMapping("/remove")
    public Result removeCategory(Integer id){
        categoryService.remove(id);
        return Result.success();
    }

    @PostMapping("/complete")
    public Result completeCategory(Integer id){
        categoryService.complete(id);
        return Result.success();
    }

}
