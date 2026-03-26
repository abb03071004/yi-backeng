package com.yibackend.mapper;

import com.yibackend.classes.entity.BookImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookImageMapper extends BaseMapper<BookImages> {

    List<String> findImagesById(Long id);

    /**
     *
     * @param imagesList
     */
    void insertBatch(@Param("imagesList") List<BookImages> imagesList);
}
