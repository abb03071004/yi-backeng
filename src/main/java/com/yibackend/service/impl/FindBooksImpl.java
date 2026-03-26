package com.yibackend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yibackend.classes.Const.OrderType;
import com.yibackend.classes.dto.BooksPageDTO;
import com.yibackend.classes.dto.SelfSalePageDTO;
import com.yibackend.classes.entity.BookContacts;
import com.yibackend.classes.entity.Books;
import com.yibackend.classes.result.PageResult;
import com.yibackend.classes.vo.BookOnGoodsListWithImageVO;
import com.yibackend.context.CurrentIdHolder;
import com.yibackend.mapper.BookContactsMapper;
import com.yibackend.mapper.BookImageMapper;
import com.yibackend.mapper.BookMapper;
import com.yibackend.service.FindBooksService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FindBooksImpl implements FindBooksService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookImageMapper bookImageMapper;

    @Autowired
    private BookContactsMapper bookContactsMapper;

    @Override
    @Transactional
    public PageResult findBooksByPara(BooksPageDTO booksPageDTO) {
        LambdaQueryWrapper<Books> lqw = new LambdaQueryWrapper<>();

        lqw.like(Books::getTitle, booksPageDTO.getSearchName()).or().like(Books::getAuthor, booksPageDTO.getSearchName()).or().like(Books::getPublisher, booksPageDTO.getSearchName());
        lqw.eq(Books::getLocation, booksPageDTO.getCampus());
        if (booksPageDTO.getOrderType() == OrderType.ASC) {
            lqw.orderByAsc(Books::getPrice);
        } else if (booksPageDTO.getOrderType() == OrderType.DESC) {
            lqw.orderByDesc(Books::getPrice);
        }else if(booksPageDTO.getOrderType()==OrderType.MIX){
            lqw.orderByDesc(Books::getScore);
        }
        log.info(booksPageDTO.toString());
        IPage<Books> booksPage = new Page<>(booksPageDTO.getPageNum(), booksPageDTO.getPageSize());
        bookMapper.selectPage(booksPage, lqw);
        List<Books> books=booksPage.getRecords();
        log.info("pageTotal"+booksPage.getTotal()+"pageSize"+booksPage.getSize()+"records"+booksPage.getRecords());

        List<BookOnGoodsListWithImageVO> res = new ArrayList<>();
        for (Books book : books) {
            BookOnGoodsListWithImageVO withImage = new BookOnGoodsListWithImageVO();
            BeanUtils.copyProperties(book, withImage);
            List<String> imagesById = bookImageMapper.findImagesById(book.getId());
            withImage.setImage(imagesById);
            res.add(withImage);
        }

        return new PageResult(booksPage.getTotal(),res);
    }

    @Override
    public Books getDetails(Integer id) {
        Books book = bookMapper.selectById(id);
        List<String> imagesById = bookImageMapper.findImagesById(book.getId());
        book.setImage(imagesById);
        return book;
    }

    @Override
    public String getContactById(Integer id) {
        BookContacts bookContacts = bookContactsMapper.selectById(id);
        return bookContacts.getNumber();
    }

    @Override
    @Transactional
    public PageResult getBooksByStatus(SelfSalePageDTO selfSalePageDTO) {

        //TODO 把用户id改成动态的
        LambdaQueryWrapper<Books> eq = new LambdaQueryWrapper<Books>().eq(Books::getStatus, selfSalePageDTO.getStatus()).eq(Books::getUserId, CurrentIdHolder.getCurrentId());
        IPage<Books> iPage=new Page<>(selfSalePageDTO.getPageNum(), selfSalePageDTO.getPageSize());
        bookMapper.selectPage(iPage, eq);
        for (Books book : iPage.getRecords()) {
            List<String> imagesById = bookImageMapper.findImagesById(book.getId());
            book.setImage(imagesById);
        }
        return new PageResult(iPage.getTotal(),iPage.getRecords());
    }
}  
