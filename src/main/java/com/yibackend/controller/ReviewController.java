package com.yibackend.controller;


import com.yibackend.classes.entity.Result;
import com.yibackend.classes.entity.Reviews;
import com.yibackend.classes.vo.ReviewInfoVO;
import com.yibackend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getReviewInfo")
    public Result<ReviewInfoVO> getReviewInfo(Integer id) {
        ReviewInfoVO res= reviewService.getReviewInfo(id);
        return Result.success(res);
    }

    @PostMapping("/submit")
    public Result submitReview(@RequestBody Reviews review) {
        reviewService.insertReview(review);

        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Reviews> getReviews( Integer id) {
         Reviews res= reviewService.getById(id);
         return Result.success(res);
    }

    @PostMapping("/update")
    public Result updateReview(@RequestBody Reviews review) {
        reviewService.updateReview(review);

        return Result.success();
    }


}
