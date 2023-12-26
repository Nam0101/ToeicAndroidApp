package com.example.englishapp.data.model;

import java.util.ArrayList;

public class TopicModel {
    private final boolean success;
    private final ArrayList<Topic> result;

    public TopicModel(boolean success, ArrayList<Topic> result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Topic> getResult() {
        return result;
    }
    public String toString() {
        return "TopicModel(success=" + this.isSuccess() + ", result=" + this.getResult() + ")";
    }

}
