package com.yibackend.service;

import com.yibackend.classes.dto.PostBooksDTO;
import com.yibackend.classes.dto.UpdateBookStatus;
import com.yibackend.classes.entity.Books;

import java.util.List;

public interface PostBooksService {
    void handlePostBooks(List<PostBooksDTO> books);

    void updateBook(Books book);

    void updateBookStatus(UpdateBookStatus updateBookStatus);
}
