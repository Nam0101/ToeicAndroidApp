package com.example.englishapp.Model;

import java.util.List;

public class DeThiModel {
    boolean success;
    String message;
    List<DeThi> result;

    public DeThiModel(boolean success, String message, List<DeThi> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DeThi> getResult() {
        return result;
    }

    public void setResult(List<DeThi> result) {
        this.result = result;
    }
}
