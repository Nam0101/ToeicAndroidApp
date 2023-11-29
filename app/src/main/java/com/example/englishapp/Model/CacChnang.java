package com.example.englishapp.Model;

public class CacChnang {
    int id;
    String tenchucnang,hinhanh;

    public CacChnang(int id, String tenchucnang, String hinhanh) {
        this.id = id;
        this.tenchucnang = tenchucnang;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
