package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.englishapp.data.model.Vocabulary;
import com.example.englishapp.databinding.FragmentVocabularyBinding;

public class VocabularyFrontFragment extends Fragment {

    private static final String ARG_VOCABULARY = "vocabulary";
    private Vocabulary vocabulary;
    FragmentVocabularyBinding binding;

    public static VocabularyFrontFragment newInstance(Vocabulary vocabulary) {
        VocabularyFrontFragment fragment = new VocabularyFrontFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_VOCABULARY, vocabulary);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vocabulary = getArguments().getParcelable(ARG_VOCABULARY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = FragmentVocabularyBinding.inflate(inflater, container, false);
            binding.tvEnglish.setText(vocabulary.getEnglish_word());
            binding.tvType.setText(vocabulary.getTopic_id());
            binding.tvVietnamese.setText(vocabulary.getVietnamese_meaning());
            // glide to load image
            Glide.with(this)
                    .load(vocabulary.getImage_url())
                    .into(binding.imageView);
            return binding.getRoot();


    }
}
