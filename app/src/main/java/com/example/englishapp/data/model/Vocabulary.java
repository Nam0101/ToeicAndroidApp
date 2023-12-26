package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Vocabulary implements Parcelable {
    private int id;
    private String topic_id;
    private String vietnamese_meaning;
    private String english_word;
    private String mp3_url;
    private String loai_tu_id;
    private String image_url;
    private String vi_du;

    protected Vocabulary(Parcel in) {
        id = in.readInt();
        topic_id = in.readString();
        vietnamese_meaning = in.readString();
        english_word = in.readString();
        mp3_url = in.readString();
        loai_tu_id = in.readString();
        image_url = in.readString();
        vi_du = in.readString();
    }

    public static final Creator<Vocabulary> CREATOR = new Creator<Vocabulary>() {
        @Override
        public Vocabulary createFromParcel(Parcel in) {
            return new Vocabulary(in);
        }

        @Override
        public Vocabulary[] newArray(int size) {
            return new Vocabulary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(topic_id);
        dest.writeString(vietnamese_meaning);
        dest.writeString(english_word);
        dest.writeString(mp3_url);
        dest.writeString(loai_tu_id);
        dest.writeString(image_url);
        dest.writeString(vi_du);
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", topic_id='" + topic_id + '\'' +
                ", vietnamese_meaning='" + vietnamese_meaning + '\'' +
                ", english_word='" + english_word + '\'' +
                ", mp3_url='" + mp3_url + '\'' +
                ", loai_tu_id='" + loai_tu_id + '\'' +
                ", image_url='" + image_url + '\'' +
                ", vi_du='" + vi_du + '\'' +
                '}';
    }

    public String getTopic_id() {
        return topic_id;
    }

    public String getVietnamese_meaning() {
        return vietnamese_meaning;
    }

    public String getEnglish_word() {
        return english_word;
    }

    public String getMp3_url() {
        return mp3_url;
    }

    public String getLoai_tu_id() {
        return loai_tu_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getVi_du() {
        return vi_du;
    }
}