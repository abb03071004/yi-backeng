package com.yibackend.service;

import com.yibackend.classes.entity.Reviews;
import com.yibackend.classes.vo.ReviewInfoVO;

public interface ReviewService {
    ReviewInfoVO getReviewInfo(Integer id);

    void insertReview(Reviews review);

    Reviews getById(Integer id);

    void updateReview(Reviews review);
}
