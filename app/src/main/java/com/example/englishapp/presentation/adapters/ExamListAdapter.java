package com.example.englishapp.presentation.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.Exam;

import java.util.ArrayList;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ExamViewHolder>{
    ArrayList<Exam> exams;
    private ExamListAdapter.OnItemClickListener listener;

    public ExamListAdapter( ArrayList<Exam> exams, ExamListAdapter.OnItemClickListener listener) {
        this.exams = exams;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Exam exam);
    }
    @NonNull
    @Override
    public ExamListAdapter.ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dethi,parent,false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListAdapter.ExamViewHolder holder, int position) {
        Exam exam = exams.get(position);
        holder.bind(exam);
        Log.i("Adapter", "onBindViewHolder: " + exam.getTendethi());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(exam));
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }
    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView tendethi,txtBodethi;

        public ExamViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            tendethi = itemView.findViewById(R.id.txtTende);
            txtBodethi = itemView.findViewById(R.id.txtBodethi);
        }
        public void bind(Exam exam){
            tendethi.setText(exam.getTendethi());
            txtBodethi.setText("Bộ đề thi: ETS"+exam.getNam());
        }
    }
}
