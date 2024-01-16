package com.example.englishapp.data.model;

import java.util.List;

public class GetExamHistoryResponse {
    private boolean success;
    private List<ExamResult> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ExamResult> getResult() {
        return result;
    }

    public void setResult(List<ExamResult> result) {
        this.result = result;
    }
}
