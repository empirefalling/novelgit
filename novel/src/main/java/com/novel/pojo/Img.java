package com.novel.pojo;

import java.sql.Blob;

public class Img {
    private byte[] img;

    public Img() {
    }

    public Img(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Img{" +
                "img=" + img +
                '}';
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}

