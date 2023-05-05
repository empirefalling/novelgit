package com.novel.mapper;

import com.novel.pojo.*;

import java.util.List;

public interface Mapper {


    List<Book> getAllBook();

    List<BookAndText> getBookAndText();

    List<Text> getBook(Integer bookid);

    Book getOneBook(Integer bookid);

    Text getPage(Integer bid);

    List<BookAndText> searchBook(String value);

    int addBook(Book book);

    Book getMaxBookid();

    List<BookAndText> getNew(Integer bookid);

    int addPage(Text text);
    int addShenhePage(Text text);

    Text getShenhePage(Integer bid);
    List<BookAndText> getShenhe();

    List<BookAndText> getAllShenhe();

    int DeleteShenhePage(Integer bid);

    int addRetreat(String status,Integer bid);

    int UpdatePage(String chapter,String page,Integer bid);

    List<Book> getShenheBook(String username);

    List<Text> getShenheList(Integer bookid);

    int DeleteBook(Integer bookid);

    int DeletePage(Integer bookid);

    int DeletePage1(Integer bookid);

    List<BookAndText> getBookChapter(Integer bookid);

    int DeleteChapter(Integer bid);

    int getstatus(String username);

    User getUser(String username);

    int setUser(User user);

    Img getImg(Integer bookid);

    void UpLoad(byte[] file1, Integer bookid);

    List<Text> getText(Integer bookid);
}
