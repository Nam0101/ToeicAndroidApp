package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Function implements Parcelable {
    private final int id;
    private final String name;
    private final String imageUrl;

    public Function(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }


    protected Function(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Function> CREATOR = new Creator<Function>() {
        @Override
        public Function createFromParcel(Parcel in) {
            return new Function(in);
        }

        @Override
        public Function[] newArray(int size) {
            return new Function[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
    }
}
