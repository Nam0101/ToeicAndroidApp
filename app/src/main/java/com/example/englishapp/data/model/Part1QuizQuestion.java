package com.example.englishapp.data.model;

public class Part1QuizQuestion implements QuizQuestion{
    private final String id;
    private final String tende;
    private final String cau;
    private final String anh;
    private final String cauhoi;
    private final String a;
    private final String b;
    private final String d;
    private final String c;
    private final String dapan;
    private final String audio;

    public Part1QuizQuestion(String id, String tende, String cau, String anh, String cauhoi, String a, String b, String d, String c, String dapan, String audio) {
        this.id = id;
        this.tende = tende;
        this.cau = cau;
        this.anh = anh;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.d = d;
        this.c = c;
        this.dapan = dapan;
        this.audio = audio;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getCauhoi() {
        return null;
    }

    @Override
    public String getDapan() {
        return null;
    }

    @Override
    public String getA() {
        return null;
    }

    @Override
    public String getB() {
        return null;
    }

    @Override
    public String getC() {
        return null;
    }

    @Override
    public String getD() {
        return null;
    }
}
