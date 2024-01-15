package com.example.englishapp.data.model;

public class ExamDateResult {
    private int user_id;
    private String exam_date;

    public ExamDateResult(int user_id, String exam_date) {
        this.user_id = user_id;
        this.exam_date = exam_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }
}