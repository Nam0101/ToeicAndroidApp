package com.example.englishapp.data.model;

public class ExamDateResponse {
    private boolean success;
    private ExamDateResult result;
    private String error;

    public ExamDateResponse(boolean success, ExamDateResult result, String error) {
        this.success = success;
        this.result = result;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ExamDateResult getResult() {
        return result;
    }

    public void setResult(ExamDateResult result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
