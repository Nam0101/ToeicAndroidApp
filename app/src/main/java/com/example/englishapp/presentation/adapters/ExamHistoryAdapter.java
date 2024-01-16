package com.example.englishapp.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.ExamResult;

import java.util.List;

public class ExamHistoryAdapter extends RecyclerView.Adapter<ExamHistoryAdapter.ExamHistoryViewHolder>{
    List<ExamResult> examResults;

    public ExamHistoryAdapter(List<ExamResult> examResults) {
        this.examResults = examResults;
    }

    @NonNull
    @Override
    public ExamHistoryAdapter.ExamHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_history, parent, false);
        return new ExamHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamHistoryAdapter.ExamHistoryViewHolder holder, int position) {
        ExamResult examResult = examResults.get(position);
        holder.txtTende.setText(examResult.getTendethi());
        holder.tvResult.setText("Kết quả: " + examResult.getCorrect_answers() + "/" + examResult.getTotal_questions());
        String timestamp = examResult.getTimestamp();
        //split and get [0] by ""
        String date = timestamp.split(" ")[0];
        holder.tvExamDate.setText("Ngày thi : " + date);
    }

    @Override
    public int getItemCount() {
        return examResults.size();
    }

    public class ExamHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtTende;
        TextView tvResult;
        TextView tvExamDate;

        public ExamHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTende = itemView.findViewById(R.id.txtTende);
            tvResult = itemView.findViewById(R.id.tv_result);
            tvExamDate = itemView.findViewById(R.id.tv_exam_date);
        }
    }
}