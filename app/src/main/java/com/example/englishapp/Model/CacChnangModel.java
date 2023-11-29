package com.example.englishapp.Model;

import java.util.List;

public class CacChnangModel {
    boolean success;
    String message;
    List<CacChnang> result;

    public CacChnangModel(boolean success, String message, List<CacChnang> result) {
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

    public List<CacChnang> getResult() {
        return result;
    }

    public void setResult(List<CacChnang> result) {
        this.result = result;
    }
}
