package com.example.englishapp.presentation.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.presentation.fragment.QuizFragment;

import java.util.ArrayList;

public class QuizPagerAdapter extends FragmentStateAdapter {
    private ArrayList<? extends QuizQuestion> questions;

    public QuizPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<? extends QuizQuestion> questions) {
        super(fragmentManager, lifecycle);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Part5QuizQuestion question = (Part5QuizQuestion) questions.get(position);
        Log.i("QuizPagerAdapter", "createFragment: " + question.getCauhoi());
        return QuizFragment.newInstance(question, position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}