package com.yibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yibackend.classes.entity.Reviews;
import com.yibackend.classes.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<Users> {
    void updateUserByReview(Reviews review);
}
