package com.yibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yibackend.classes.entity.Reviews;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper extends BaseMapper<Reviews> {
}
