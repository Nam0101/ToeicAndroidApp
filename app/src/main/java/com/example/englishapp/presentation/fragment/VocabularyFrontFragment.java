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
import com.example.englishapp.domain.CacheManager;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    String loaiTuId2Loaitu(String loaiTuId) {
        int loaiTuIdInt = Integer.parseInt(loaiTuId);
        switch (loaiTuIdInt) {
            case 2:
                return "noun";
            case 3:
                return "adjective";
            case 4:
                return "phrasal verb";
            case 5:
                return "adverb";
            default:
                return "verb";
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = FragmentVocabularyBinding.inflate(inflater, container, false);
        binding.tvEnglish.setText(vocabulary.getEnglish_word());
        binding.tvType.setText(loaiTuId2Loaitu(vocabulary.getLoai_tu_id()));
        binding.tvVietnamese.setText(vocabulary.getVietnamese_meaning());
        Future<File> future = CacheManager.cacheImageFile(getContext(), vocabulary.getImage_url());
        try {
            File cacheFile = future.get();
            Glide.with(this).load(cacheFile).into(binding.imageView);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
            return binding.getRoot();


    }
}
