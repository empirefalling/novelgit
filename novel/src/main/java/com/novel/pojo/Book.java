package com.novel.pojo;

public class Book {

    private Integer img;
    private Integer bookid;
    private String bookname;
    private String tip;
    private String author;

    private String intro;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Book() {
    }

    public Book(Integer img,Integer bookid, String bookname, String tip, String author, String intro) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.tip = tip;
        this.author = author;
        this.intro = intro;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", img='" + img + '\'' +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", tip='" + tip + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
