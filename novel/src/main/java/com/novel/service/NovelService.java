package com.novel.service;

import com.github.pagehelper.PageInfo;
import com.novel.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface NovelService {



    PageInfo<Book> getBookPage(Integer pageNum);
    PageInfo<BookAndText> getBookAndTextPage(Integer pageNum);

    List<Text> getBook(Integer bookid);

    Book getOneBook(Integer bookid);

    Text getPage(Integer bid);

    PageInfo<BookAndText> searchBook(Integer pageNum, String value);

    int addBook(Book book);

    Book getMaxBookid();

    PageInfo<BookAndText> getNew(Integer pageNum,Integer bookid);

    int addPage(Text text);

    int addShenhePage(Text text);

    Text getShenhePage(Integer bid);

    PageInfo<BookAndText> getShenhe(Integer pageNum);

    List<BookAndText> getAllShenhe();

    int DeleteShenhePage(Integer bid);

    int addRetreat(String status,Integer bid);

    int UpdatePage(String chapter,String page,Integer bid);

    List<Book> getShenheBook(String username);

    List<Text> getShenheList(Integer bookid);

    int DeleteBook(Integer bookid);

    int DeletePage(Integer bookid);

    List<BookAndText> getBookChapter(Integer bookid);

    int DeleteChapter(Integer bid);

    int getstatus(String username);

    User getUser(String username);

    int setUser(User user);

    void outputBookImg(HttpServletRequest request, HttpServletResponse response, Integer bookid)
            throws ServletException, IOException, SQLException;


    void UpLoad(byte[] file1, Integer bookid);

    List<Text> getText(Integer bookid);
}
