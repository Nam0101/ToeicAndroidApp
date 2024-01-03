package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Part2QuizQuestion implements Parcelable, QuizQuestion {
    public static final Creator<Part2QuizQuestion> CREATOR = new Creator<Part2QuizQuestion>() {
        @Override
        public Part2QuizQuestion createFromParcel(Parcel in) {
            return new Part2QuizQuestion(in);
        }

        @Override
        public Part2QuizQuestion[] newArray(int size) {
            return new Part2QuizQuestion[size];
        }
    };
    private final String id;
    private final String tende;
    private final String cau;
    private final String cauhoi;
    private final String a;
    private final String b;
    private final String c;
    private final String dapan;
    private final String amthanh;

    public Part2QuizQuestion(String id, String tende, String cau, String cauhoi, String a, String b, String c, String dapan, String amthanh) {
        this.id = id;
        this.tende = tende;
        this.cau = cau;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.c = c;
        this.dapan = dapan;
        this.amthanh = amthanh;
    }

    protected Part2QuizQuestion(Parcel in) {
        id = in.readString();
        tende = in.readString();
        cau = in.readString();
        cauhoi = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        dapan = in.readString();
        amthanh = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tende);
        dest.writeString(cau);
        dest.writeString(cauhoi);
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(dapan);
        dest.writeString(amthanh);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCauhoi() {
        return cauhoi;
    }

    @Override
    public String getDapan() {
        return dapan;
    }

    @Override
    public String getA() {
        return a;
    }

    @Override
    public String getB() {
        return b;
    }

    @Override
    public String getC() {
        return c;
    }

    @Override
    public String getD() {
        return null;
    }
    public String getTende() {
        return tende;
    }
    public String getCau() {
        return cau;
    }
    public String getAmthanh() {
        return amthanh;
    }
}