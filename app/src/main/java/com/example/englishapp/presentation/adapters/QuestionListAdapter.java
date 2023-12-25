package com.example.englishapp.presentation.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {
    private final ArrayList<? extends QuizQuestion> questions;
    private final QuizSharedViewModel viewModel;
    private final OnItemClickListener listener;
    private final RecyclerView recyclerView;
    private int selectedPosition = -1;

    public QuestionListAdapter(ArrayList<? extends QuizQuestion> questions, QuizSharedViewModel viewModel, OnItemClickListener listener, RecyclerView recyclerView) {
        this.questions = questions;
        this.viewModel = viewModel;
        this.listener = listener;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_list, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.bind(questions.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setSelectedPosition(int position) {
        int oldPosition = this.selectedPosition;
        this.selectedPosition = position;
        notifyItemChanged(oldPosition);
        notifyItemChanged(position);
        Objects.requireNonNull(recyclerView.getLayoutManager()).smoothScrollToPosition(recyclerView, new RecyclerView.State(), position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.question_text);
        }

        void bind(QuizQuestion question, int position, OnItemClickListener listener) {
            textView.setText(String.valueOf(position + 1));
            if (Objects.requireNonNull(viewModel.answeredQuestions.getValue()).contains(position)) {
                itemView.setBackgroundColor(Color.GREEN); // Change color if answered
            } else if (position == selectedPosition) {
                itemView.setBackgroundColor(Color.YELLOW); // Change color if selected
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT); // Default color
            }
            itemView.setOnClickListener(v -> {
                setSelectedPosition(position);
                listener.onItemClick(position);
            });
        }
    }
}