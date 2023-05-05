package com.novel.pojo;

import java.sql.Timestamp;

public class Text {
    private Integer bookid;
    private Integer bid;
    private String chapter;
    private String page;
    private String status;
    private Timestamp addtime;

    @Override
    public String toString() {
        return "Text{" +
                "bookid=" + bookid +
                ", bid=" + bid +
                ", chapter='" + chapter + '\'' +
                ", page='" + page + '\'' +
                ", status=" + status +
                ", addtime=" + addtime +
                '}';
    }

    public Text(Integer bookid, Integer bid, String chapter, String page, String status, Timestamp addtime) {
        this.bookid = bookid;
        this.bid = bid;
        this.chapter = chapter;
        this.page = page;
        this.status = status;
        this.addtime = addtime;
    }

    public Text() {
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

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }


}
