package com.yibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yibackend.classes.Const.MessageTypeConst;
import com.yibackend.classes.entity.*;
import com.yibackend.classes.vo.ReviewInfoVO;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.*;
import com.yibackend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReviewImpl implements ReviewService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public ReviewInfoVO getReviewInfo(Integer id) {
        Books books = bookMapper.selectById(id);
        Users users = userMapper.selectById(books.getUserId());
        return ReviewInfoVO.builder()
                .userId(users.getId())
                .nickname(users.getNickname())
                .build();
    }

    @Override
    @Transactional
    public void insertReview(Reviews review) {
        //TODO把id改成动态的
        review.setFromUserId(CurrentIdHolder.getCurrentId());
        review.setCreatedTime(LocalDateTime.now());
        reviewMapper.insert(review);

        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Category::getId, review.getCartItemId()).set(Category::getStatus, 2);
        categoryMapper.update(null, updateWrapper);

        userMapper.updateUserByReview(review);

        Message message = new  Message();
        message.setUserId(review.getToUserId());
        message.setType(MessageTypeConst.REVIEW);
        message.setTitle("有用户给了您"+review.getRating()+"星评价");
        message.setRelatedId(review.getId());
        messageMapper.insert(message);

    }

    @Override
    public Reviews getById(Integer id) {
        return reviewMapper.selectById(id);
    }

    @Override
    public void updateReview(Reviews review) {
        //TODO 后边把id改成动态的
        review.setFromUserId(CurrentIdHolder.getCurrentId());
        reviewMapper.updateById(review);
    }
}
