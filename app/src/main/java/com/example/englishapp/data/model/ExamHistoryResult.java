package com.example.englishapp.data.model;

public class ExamHistoryResult {
    private int user_id;
    private int exam_id;
    private int correct_answer;
    private int total_question;
    private String time_stamp;

    public ExamHistoryResult(int user_id, int exam_id, int correct_answer, int total_question, String time_stamp) {
        this.user_id = user_id;
        this.exam_id = exam_id;
        this.correct_answer = correct_answer;
        this.total_question = total_question;
        this.time_stamp = time_stamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getTotal_question() {
        return total_question;
    }

    public void setTotal_question(int total_question) {
        this.total_question = total_question;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}