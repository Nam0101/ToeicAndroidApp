package com.example.englishapp.data.model;

public class ExamHistoryResponse {
    private boolean success;
    private ExamHistoryResult result;
    private String error;

    public ExamHistoryResponse(boolean success, ExamHistoryResult result, String error) {
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

    public ExamHistoryResult getResult() {
        return result;
    }

    public void setResult(ExamHistoryResult result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
