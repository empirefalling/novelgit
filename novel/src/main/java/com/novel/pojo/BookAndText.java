package com.novel.pojo;

public class BookAndText {

    private Integer img;
    private Integer bookid;
    private Integer bid;
    private String bookname;
    private String tip;
    private String author;
    private String intro;
    private String chapter;
    private String addtime;
    private String page;
    private String status;

    public BookAndText(Integer img,Integer bookid, Integer bid, String bookname, String tip, String author, String intro, String chapter, String addtime, String page, String status) {
        this.img = img;
        this.bookid = bookid;
        this.bid = bid;
        this.bookname = bookname;
        this.tip = tip;
        this.author = author;
        this.intro = intro;
        this.chapter = chapter;
        this.addtime = addtime;
        this.page = page;
        this.status = status;
    }

    public BookAndText() {
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

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.status = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookAndText{" +
                ", img='" + img + '\'' +
                "bookid=" + bookid +
                ", bid=" + bid +
                ", bookname='" + bookname + '\'' +
                ", tip='" + tip + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", chapter='" + chapter + '\'' +
                ", addtime='" + addtime + '\'' +
                ", page='" + page + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
