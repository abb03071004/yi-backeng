package com.yibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yibackend.classes.Const.CategoryStatusConst;
import com.yibackend.classes.Const.IsDeleted;
import com.yibackend.classes.dto.CartPageDTO;
import com.yibackend.classes.entity.Books;
import com.yibackend.classes.entity.Category;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.BookOnGoodsListWithImageVO;
import com.yibackend.classes.vo.CategoryVO;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.BookImageMapper;
import com.yibackend.mapper.BookMapper;
import com.yibackend.mapper.CategoryMapper;
import com.yibackend.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookImageMapper bookImageMapper;

    @Override
    @Transactional
    public PageResult getCategory(CartPageDTO cartPageDTO) {
        //TODO把用户id换成动态的
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<Category>().eq(Category::getUserId, CurrentIdHolder.getCurrentId())
                .eq(Category::getIsDeleted, IsDeleted.FALSE).orderByAsc(Category::getAddedTime);
        if(cartPageDTO.getStatus()!=null){
            lqw.eq(Category::getStatus,cartPageDTO.getStatus());
        }
        IPage<Category> iPage=new Page<>(cartPageDTO.getPageNum(),cartPageDTO.getPageSize());
        categoryMapper.selectPage(iPage,lqw);
        List<Category> categories = iPage.getRecords();
        List<CategoryVO> res = new ArrayList<>();


        List<Long> bookIds = categories.stream().map(Category::getBookId).collect(Collectors.toList());
        List<Books> books = bookMapper.selectBatchIds(bookIds);

        for (int i = 0; i < books.size(); i++) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(books.get(i), categoryVO);
            List<String> imagesById = bookImageMapper.findImagesById(bookIds.get(i));
            categoryVO.setCategoryId(categories.get(i).getId());
            categoryVO.setImage(imagesById);
            categoryVO.setStatus(categories.get(i).getStatus());
            res.add(categoryVO);
        }
        return new PageResult(iPage.getTotal(),res);
    }

    @Override
    public void remove(Integer id) {
        categoryMapper.update(null, new LambdaUpdateWrapper<Category>().eq(Category::getId, id).set(Category::getIsDeleted, IsDeleted.TRUE));
    }

    @Override
    public void complete(Integer id) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Category::getId, id).set(Category::getStatus, CategoryStatusConst.COMPLETED);
        categoryMapper.update(null, updateWrapper);
    }
}
