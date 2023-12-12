package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Part5QuizQuestion implements Parcelable, QuizQuestion {
    private final String id;
    private final String nam;
    private final String made;
    private final String cau;
    private final String cauhoi;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String dapan;

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

    @Override
    public String getCauhoi() {
        return cauhoi;
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

    @Override
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