package com.yibackend.mapper;

import com.yibackend.classes.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Books> {

    @Update("        update books left join users u on books.user_id = u.id\n" +
            "        set books.score\n" +
            "                = u.rating * 0.2 + u.completed_trades * 0.2 - books.`condition` * 0.2 - books.price * 0.2\n" +
            "                - datediff(NOW(), books.create_time) * 0.1")
     static void calculateScore() {
    }

    /**
     *
     * @param booksList
     */
    void insertBatch(@Param("booksList") List<Books> booksList);
}
