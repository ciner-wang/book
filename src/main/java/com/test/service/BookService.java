package com.test.service;

import com.test.pojo.Book;
import com.test.pojo.BookClass;
import com.test.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookService {
    Result getBookList(Integer page, Integer limit);

    Result deleteBatch(Integer[] ids);

    Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request);

    Result removeFile(String removeTarget, HttpServletRequest request);

    Result addBook(Book book);

    Result search(String timerange,String keyword, Integer page, Integer limit);
    Book selectByID(Integer bookId);

    Result updateBook(Book book);

}
