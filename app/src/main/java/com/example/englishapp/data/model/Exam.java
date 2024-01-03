package com.example.englishapp.data.model;

public class Exam {
    private String id;
    private String tendethi;
    private String nam;
    private String made;

    public Exam(String id, String tendethi, String nam, String made) {
        this.id = id;
        this.tendethi = tendethi;
        this.nam = nam;
        this.made = made;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}