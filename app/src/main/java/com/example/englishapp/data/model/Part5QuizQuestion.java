package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Part5QuizQuestion  implements Parcelable {
    private String id;
    private String nam;
    private String made;
    private String cau;
    private String cauhoi;
    private String a;
    private String b;
    private String c;
    private String d;
    private String dapan;

    public Part5QuizQuestion(String id, String nam, String made, String cau, String cauhoi, String a, String b, String c, String d, String dapan) {
        this.id = id;
        this.nam = nam;
        this.made = made;
        this.cau = cau;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
    }

    protected Part5QuizQuestion(Parcel in) {
        id = in.readString();
        nam = in.readString();
        made = in.readString();
        cau = in.readString();
        cauhoi = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        d = in.readString();
        dapan = in.readString();
    }

    public static final Creator<Part5QuizQuestion> CREATOR = new Creator<Part5QuizQuestion>() {
        @Override
        public Part5QuizQuestion createFromParcel(Parcel in) {
            return new Part5QuizQuestion(in);
        }

        @Override
        public Part5QuizQuestion[] newArray(int size) {
            return new Part5QuizQuestion[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNam() {
        return nam;
    }

    public String getMade() {
        return made;
    }

    public String getCau() {
        return cau;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getDapan() {
        return dapan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nam);
        dest.writeString(made);
        dest.writeString(cau);
        dest.writeString(cauhoi);
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(d);
        dest.writeString(dapan);
    }
}