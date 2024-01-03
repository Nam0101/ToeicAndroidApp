package com.example.englishapp.presentation.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.QuestionResult;

import java.util.List;

public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.QuestionResultViewHolder> {
    private final List<QuestionResult> questionResults;
    private final OnItemClickListener listener;

    public QuestionResultAdapter(List<QuestionResult> questionResults, OnItemClickListener listener) {
        this.questionResults = questionResults;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
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
        int trueAnswer = questionResult.getAnswer();
        int selectedAnswer = questionResult.getSelectedAnswer();
        holder.textViewQuestionNumber.setText(String.valueOf(questionResult.getPosition() + 1));
        if (trueAnswer == 1) {
            holder.answerA.setBackgroundResource(R.color.green);
        } else if (trueAnswer == 2) {
            holder.answerB.setBackgroundResource(R.color.green);
        } else if (trueAnswer == 3) {
            holder.answerC.setBackgroundResource(R.color.green);
        } else if (trueAnswer == 4) {
            holder.answerD.setBackgroundResource(R.color.green);
        }
        Log.i("QuestionResultAdapter", "onBindViewHolder: " + selectedAnswer);
        Log.i("QuestionResultAdapter", "onBindViewHolder: " + trueAnswer);
        if(!questionResult.isCorrect()){
            if (selectedAnswer == 1) {
                holder.answerA.setBackgroundResource(R.color.red);
            } else if (selectedAnswer == 2) {
                holder.answerB.setBackgroundResource(R.color.red);
            } else if (selectedAnswer == 3) {
                holder.answerC.setBackgroundResource(R.color.red);
            } else if (selectedAnswer == 4) {
                holder.answerD.setBackgroundResource(R.color.red);
            }
        }
        holder.linearLayout.setOnClickListener(v -> {
            listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return questionResults.size();
    }

    static class QuestionResultViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestionNumber;
        View answerA;
        View answerB;
        View answerC;
        View answerD;
        LinearLayout linearLayout;
        QuestionResultViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestionNumber = itemView.findViewById(R.id.question_number);
            answerA = itemView.findViewById(R.id.answer_a);
            answerB = itemView.findViewById(R.id.answer_b);
            answerC = itemView.findViewById(R.id.answer_c);
            answerD = itemView.findViewById(R.id.answer_d);
            linearLayout = itemView.findViewById(R.id.question_container);
        }
    }
}