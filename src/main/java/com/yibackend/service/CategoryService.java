package com.yibackend.service;


import com.yibackend.classes.dto.CartPageDTO;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    PageResult getCategory(CartPageDTO cartPageDTO);

    void remove(Integer id);

    void complete(Integer id);
}
