package com.example.englishapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Topic implements Parcelable {
    public final int id;
    public final String topic;

    public Topic(int id, String name) {
        this.id = id;
        this.topic = name;
    }

    protected Topic(Parcel in) {
        id = in.readInt();
        topic = in.readString();
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(topic);
    }
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + topic + '\'' +
                '}';
    }
}
