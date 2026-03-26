package com.yibackend.service;


import com.yibackend.classes.dto.BooksPageDTO;
import com.yibackend.classes.dto.SelfSalePageDTO;
import com.yibackend.classes.entity.Books;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.BookOnGoodsListWithImageVO;

import java.util.List;

public interface FindBooksService {
    PageResult findBooksByPara(BooksPageDTO booksPageDTO);

    Books getDetails(Integer id);

    String getContactById(Integer id);

    PageResult getBooksByStatus(SelfSalePageDTO selfSalePageDTO);
}
