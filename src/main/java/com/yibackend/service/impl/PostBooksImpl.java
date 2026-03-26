package com.yibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yibackend.classes.Const.BookStatus;
import com.yibackend.classes.dto.PostBooksDTO;
import com.yibackend.classes.dto.UpdateBookStatus;
import com.yibackend.classes.entity.BookContacts;
import com.yibackend.classes.entity.BookImages;
import com.yibackend.classes.entity.Books;
import com.yibackend.mapper.BookContactsMapper;
import com.yibackend.mapper.BookImageMapper;
import com.yibackend.mapper.BookMapper;
import com.yibackend.service.PostBooksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostBooksImpl implements PostBooksService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookImageMapper bookImageMapper;

    @Autowired
    private BookContactsMapper bookContactsMapper;

    @Override
    @Transactional
    public void handlePostBooks(List<PostBooksDTO> books) {
        List<Books> booksList = new ArrayList<>();
        for (PostBooksDTO book : books) {
            Books book1 = new Books();
            BeanUtils.copyProperties(book, book1);
            book1.setCreateTime(LocalDateTime.now());
            book1.setUpdateTime(LocalDateTime.now());
            book1.setStatus(BookStatus.ONSALE);
            //TODO 给新用户加点score让体验感增强
            book1.setScore(new BigDecimal(5)
                    .subtract(book.getPrice().multiply(new BigDecimal("0.2")))
                    .subtract(new BigDecimal(book.getCondition()).multiply(new BigDecimal("0.2"))));
            booksList.add(book1);
        }
        bookMapper.insertBatch(booksList);
        List<BookImages> imagesList = new ArrayList<>();
        for(int i=0;i<books.size();i++){
            for(int j=0;j<books.get(i).getImages().size();j++){
                BookImages bookImages = new BookImages();
                bookImages.setBookId(booksList.get(i).getId());
                bookImages.setImageUrl(books.get(i).getImages().get(j));
                bookImages.setSortOrder(j);
                bookImages.setCreateTime(LocalDateTime.now());
                imagesList.add(bookImages);
            }
        }
        if (!imagesList.isEmpty()) {
            bookImageMapper.insertBatch(imagesList);
        }

        List<BookContacts> bookContactsList = new ArrayList<>();
        for(int i=0;i<books.size();i++){
            BookContacts bookContacts = new BookContacts();
            bookContacts.setBookId(booksList.get(i).getId());
            bookContacts.setNumber(books.get(i).getNumber());
            bookContacts.setCreateTime(LocalDateTime.now());
            bookContactsList.add(bookContacts);
        }
        bookContactsMapper.insertBatch(bookContactsList);
    }

    @Override
    public void updateBook(Books book) {
        bookMapper.updateById(book);
    }

    @Override
    public void updateBookStatus(UpdateBookStatus updateBookStatus) {
        LambdaUpdateWrapper<Books> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Books::getId, updateBookStatus.getId()).set(Books::getStatus, updateBookStatus.getStatus());
        bookMapper.update(null, updateWrapper);
    }
}
