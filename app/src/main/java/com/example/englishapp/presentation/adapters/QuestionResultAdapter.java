package com.example.englishapp.presentation.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.QuestionResult;

import java.util.List;

public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.QuestionResultViewHolder> {
    private final List<QuestionResult> questionResults;

    public QuestionResultAdapter(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

    @NonNull
    @Override
    public QuestionResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_result, parent, false);
        return new QuestionResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionResultViewHolder holder, int position) {
        QuestionResult questionResult = questionResults.get(position);
        holder.questionNumber.setText(String.valueOf(position + 1));
        holder.colorIndicator.setBackgroundColor(questionResult.isCorrect() ? Color.GREEN : Color.RED);
    }

    @Override
    public int getItemCount() {
        return questionResults.size();
    }

    static class QuestionResultViewHolder extends RecyclerView.ViewHolder {
        final TextView questionNumber;
        final View colorIndicator;

        QuestionResultViewHolder(@NonNull View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.question_number);
            colorIndicator = itemView.findViewById(R.id.color_indicator);
        }
    }
}