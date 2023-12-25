package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Part7QuizQuestion implements Parcelable, QuizQuestion {
    public static final Creator<Part7QuizQuestion> CREATOR = new Creator<Part7QuizQuestion>() {
        @Override
        public Part7QuizQuestion createFromParcel(Parcel in) {
            return new Part7QuizQuestion(in);
        }

        @Override
        public Part7QuizQuestion[] newArray(int size) {
            return new Part7QuizQuestion[size];
        }
    };
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

    protected Part7QuizQuestion(Parcel in) {
        id = in.readString();
        tende = in.readString();
        cau = in.readString();
        anh = in.readString();
        cauhoi = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        d = in.readString();
        dapan = in.readString();
    }

    public Part7QuizQuestion(String id, String tende, String cau, String anh, String cauhoi, String a, String b, String c, String d, String dapan) {
        this.id = id;
        this.tende = tende;
        this.cau = cau;
        this.anh = anh;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tende);
        dest.writeString(cau);
        dest.writeString(anh);
        dest.writeString(cauhoi);
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(d);
        dest.writeString(dapan);
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
        return d;
    }

    public String getTende() {
        return tende;
    }

    public String getCau() {
        return cau;
    }

    public String getAnh() {
        return anh;
    }

    public String toString() {
        return "Part7QuizQuestion{" +
                "id='" + id + '\'' +
                ", tende='" + tende + '\'' +
                ", cau='" + cau + '\'' +
                ", anh='" + anh + '\'' +
                ", cauhoi='" + cauhoi + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", d='" + d + '\'' +
                ", c='" + c + '\'' +
                ", dapan='" + dapan + '\'' +
                '}';
    }
}
