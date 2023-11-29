package com.example.englishapp.Model;

public class DeThi {
    int id;
    String tendethi,nam,made;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendethi() {
        return tendethi;
    }

    public void setTendethi(String tendethi) {
        this.tendethi = tendethi;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public DeThi(int id, String tendethi, String nam, String made) {
        this.id = id;
        this.tendethi = tendethi;
        this.nam = nam;
        this.made = made;
    }
}
