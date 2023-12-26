package com.example.englishapp.data.model;

import java.util.List;

public class VocabularyModel {
    private boolean success;
    private List<Vocabulary> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Vocabulary> getResult() {
        return result;
    }

    public void setResult(List<Vocabulary> result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "VocabularyModel{" +
                "success=" + success +
                ", result=" + result +
                '}';
    }
}