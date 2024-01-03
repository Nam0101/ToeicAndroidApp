package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Part4QuizQuestion implements Parcelable, QuizQuestion{
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
    private final String amthanh;

    public Part4QuizQuestion(String id, String tende, String cau, String anh, String cauhoi, String a, String b, String d, String c, String dapan, String audio) {
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
        this.amthanh = audio;
    }

    protected Part4QuizQuestion(Parcel in) {
        id = in.readString();
        tende = in.readString();
        cau = in.readString();
        anh = in.readString();
        cauhoi = in.readString();
        a = in.readString();
        b = in.readString();
        d = in.readString();
        c = in.readString();
        dapan = in.readString();
        amthanh = in.readString();
    }

    public static final Creator<Part4QuizQuestion> CREATOR = new Creator<Part4QuizQuestion>() {
        @Override
        public Part4QuizQuestion createFromParcel(Parcel in) {
            return new Part4QuizQuestion(in);
        }

        @Override
        public Part4QuizQuestion[] newArray(int size) {
            return new Part4QuizQuestion[size];
        }
    };

    @Override
    public String getId() {
        return id;
    }
    public String getAnh() {
        return anh;
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
    public String getAmthanh() {
        return amthanh;
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
        dest.writeString(d);
        dest.writeString(c);
        dest.writeString(dapan);
        dest.writeString(amthanh);
    }
    @NonNull
    @Override
    public String toString() {
        return "Part4QuizQuestion{" +
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
                ", amthanh='" + amthanh + '\'' +
                '}';
    }
}
