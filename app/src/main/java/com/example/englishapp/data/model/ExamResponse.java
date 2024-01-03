package com.example.englishapp.data.model;

import java.util.List;

public class ExamResponse {
    private boolean success;
    private List<Exam> result;

    public ExamResponse(boolean success, List<Exam> result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Exam> getResult() {
        return result;
    }

    public void setResult(List<Exam> result) {
        this.result = result;
    }
}
