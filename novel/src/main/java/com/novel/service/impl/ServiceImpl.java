package com.novel.service.impl;

import com.alibaba.druid.mock.MockCallableStatement;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.novel.mapper.Mapper;
import com.novel.pojo.*;
import com.novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@Service
@Transactional
public class ServiceImpl implements NovelService {

    @Autowired
    private Mapper Mapper;

    @Override
    public PageInfo<Book> getBookPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 20);
        //查询所有的员工信息
        List<Book> list = Mapper.getAllBook();
        //获取分页相关数据
        PageInfo<Book> page = new PageInfo<>(list, 5);
        return page;
    }


    @Override
    public PageInfo<BookAndText> getBookAndTextPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 20);
        //查询所有的员工信息
        List<BookAndText> list = Mapper.getBookAndText();
        //获取分页相关数据
        PageInfo<BookAndText> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public List<Text> getBook(Integer bookid) {
        return Mapper.getBook(bookid);
    }

    @Override
    public Book getOneBook(Integer bookid) {
        return Mapper.getOneBook(bookid);
    }

    @Override
    public Text getPage(Integer bid) {
        return Mapper.getPage(bid);
    }

    @Override
    public PageInfo<BookAndText> searchBook(Integer pageNum, String value) {
        //开启分页功能
        PageHelper.startPage(pageNum, 20);
        //查询所有的员工信息
        List<BookAndText> list = Mapper.searchBook(value);
        //获取分页相关数据
        PageInfo<BookAndText> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public int addBook(Book book) {
        return Mapper.addBook(book);
    }

    @Override
    public Book getMaxBookid() {
        return Mapper.getMaxBookid();
    }

    @Override
    public PageInfo<BookAndText> getNew(Integer pageNum,Integer bookid){
        //开启分页功能
        PageHelper.startPage(pageNum, 20);
        //查询所有的员工信息
        List<BookAndText> list = Mapper.getNew(bookid);
        //获取分页相关数据
        PageInfo<BookAndText> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public int addPage(Text text) {
        return Mapper.addPage(text);
    }

    @Override
    public int addShenhePage(Text text) {
        return Mapper.addShenhePage(text);
    }

    @Override
    public Text getShenhePage(Integer bid) {
        return Mapper.getShenhePage(bid);
    }

    @Override
    public PageInfo<BookAndText> getShenhe(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 20);
        //查询所有的员工信息
        List<BookAndText> list = Mapper.getShenhe();
        //获取分页相关数据
        PageInfo<BookAndText> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public List<BookAndText> getAllShenhe() {
        return Mapper.getAllShenhe();
    }

    @Override
    public int DeleteShenhePage(Integer bid) {
        return Mapper.DeleteShenhePage(bid);
    }

    @Override
    public int addRetreat(String status,Integer bid) {
        return Mapper.addRetreat(status,bid);
    }

    @Override
    public int UpdatePage(String chapter,String page,Integer bid) {
        return Mapper.UpdatePage(chapter,page,bid);
    }

    @Override
    public List<Book> getShenheBook(String username) {
        return Mapper.getShenheBook(username);
    }

    @Override
    public List<Text> getShenheList(Integer bookid) {
        return Mapper.getShenheList(bookid);
    }

    @Override
    public int DeleteBook(Integer bookid) {
        return Mapper.DeleteBook(bookid);
    }

    @Override
    public int DeletePage(Integer bookid){
        Mapper.DeletePage(bookid);
        Mapper.DeletePage1(bookid);
        return 2;
    }

    @Override
    public List<BookAndText> getBookChapter(Integer bookid) {
        return Mapper.getBookChapter(bookid);
    }

    @Override
    public int DeleteChapter(Integer bid) {
        return Mapper.DeleteChapter(bid);
    }

    @Override
    public int getstatus(String username) {
        return Mapper.getstatus(username);
    }

    @Override
    public User getUser(String username) {
        return Mapper.getUser(username);
    }

    @Override
    public int setUser(User user) {
        return Mapper.setUser(user);
    }

    @Override
    public void outputBookImg(HttpServletRequest request, HttpServletResponse response, Integer bookid)
            throws ServletException, IOException, SQLException {



        Img img = Mapper.getImg(bookid);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(img.getImg());
        outputStream.flush();
        outputStream.close();//关闭流

    }

    @Override
    public void UpLoad(byte[] file1, Integer bookid) {
        Mapper.UpLoad(file1,bookid);
    }

    @Override
    public List<Text> getText(Integer bookid) {
        return Mapper.getText(bookid);
    }


}
