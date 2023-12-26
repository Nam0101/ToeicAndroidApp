package com.example.englishapp.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.englishapp.data.model.Vocabulary;
import com.example.englishapp.presentation.fragment.VocabularyBackFragment;
import com.example.englishapp.presentation.fragment.VocabularyFrontFragment;

import java.util.List;

public class VocabularyPagerAdapter extends FragmentStateAdapter {
    private final List<Vocabulary> vocabularies;

    public VocabularyPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Vocabulary> vocabularies) {
        super(fragmentActivity);
        this.vocabularies = vocabularies;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Get the actual index of the vocabulary in the list
        int index = position / 2;

        if (position % 2 == 0) {
            // If position is even, show the front of the vocabulary
            return VocabularyFrontFragment.newInstance(vocabularies.get(index));
        } else {
            // If position is odd, show the back of the vocabulary
            return VocabularyBackFragment.newInstance(vocabularies.get(index));
        }
    }

    @Override
    public int getItemCount() {
        return vocabularies.size() * 2;

    }
}