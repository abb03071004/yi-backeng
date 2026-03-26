package com.yibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yibackend.classes.entity.BookContacts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookContactsMapper extends BaseMapper<BookContacts> {
    /**
     *
     * @param bookContactsList
     */
    void insertBatch(@Param("bookContactsList") List<BookContacts> bookContactsList);
}
