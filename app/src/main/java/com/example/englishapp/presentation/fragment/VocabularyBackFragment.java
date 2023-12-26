package com.example.englishapp.presentation.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.englishapp.data.model.Vocabulary;
import com.example.englishapp.databinding.FragmentBackVocabularyBinding;

import java.io.IOException;

public class VocabularyBackFragment extends Fragment {
    private static final String ARG_VOCABULARY = "vocabulary";
    private Vocabulary vocabulary;
    FragmentBackVocabularyBinding binding;
    private MediaPlayer mediaPlayer;
    public static VocabularyBackFragment newInstance(Vocabulary vocabulary) {
        VocabularyBackFragment fragment = new VocabularyBackFragment();
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
    binding = FragmentBackVocabularyBinding.inflate(inflater, container, false);
    binding.tvExample.setText(vocabulary.getVi_du());
    binding.imageButton.setOnClickListener(v -> {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(vocabulary.getMp3_url());
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            binding.imageButton.setImageResource(android.R.drawable.ic_media_pause);
            mediaPlayer.setOnCompletionListener(mp -> binding.imageButton.setImageResource(android.R.drawable.ic_media_play));
        } else if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            binding.imageButton.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mediaPlayer.start();
            binding.imageButton.setImageResource(android.R.drawable.ic_media_pause);
        }
    });
    return binding.getRoot();
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}