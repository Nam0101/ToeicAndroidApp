package com.example.englishapp.presentation.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.englishapp.data.model.Part1QuizQuestion;
import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.data.model.Part6QuizQuestion;
import com.example.englishapp.data.model.Part7QuizQuestion;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.presentation.fragment.QuizPart1Fragment;
import com.example.englishapp.presentation.fragment.QuizPart5Fragment;
import com.example.englishapp.presentation.fragment.QuizPart6Fragment;
import com.example.englishapp.presentation.fragment.QuizPart7Fragment;

import java.util.ArrayList;

public class QuizPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<? extends QuizQuestion> questions;

    public QuizPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<? extends QuizQuestion> questions) {
        super(fragmentManager, lifecycle);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(questions.get(position) instanceof Part1QuizQuestion) {
            Part1QuizQuestion question = (Part1QuizQuestion) questions.get(position);
            Log.i("QuizPagerAdapter", "createFragment: for part 1");
            return QuizPart1Fragment.newInstance(question, position);
        }
       else if (questions.get(position) instanceof Part5QuizQuestion) {
            Part5QuizQuestion question = (Part5QuizQuestion) questions.get(position);
            return QuizPart5Fragment.newInstance(question, position);
        } else if (questions.get(position) instanceof Part6QuizQuestion) {
            Part6QuizQuestion question = (Part6QuizQuestion) questions.get(position);
            return QuizPart6Fragment.newInstance(question, position); // Changed this line
        } else if (questions.get(position) instanceof Part7QuizQuestion) {
            Part7QuizQuestion question = (Part7QuizQuestion) questions.get(position);
            return QuizPart7Fragment.newInstance(question, position); // Changed this line
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}