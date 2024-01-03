package com.example.englishapp.presentation.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.englishapp.BuildConfig;
import com.example.englishapp.R;
import com.example.englishapp.data.model.Part1QuizQuestion;
import com.example.englishapp.data.model.QuestionResult;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.databinding.FragmentPart1QuizBinding;
import com.example.englishapp.domain.CacheManager;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class QuizPart1Fragment extends Fragment {
    private QuizSharedViewModel quizSharedViewModel;
    private QuizQuestion question;
    private int position;
    private int selectedAnswer;
    private QuizViewModel quizViewModel;
    private MediaPlayer mediaPlayer;
    private final Handler handler = new Handler();
    FragmentPart1QuizBinding binding;
    private int trueAnswer=0;

    public static QuizPart1Fragment newInstance(QuizQuestion question, int position) {
        QuizPart1Fragment fragment = new QuizPart1Fragment();
        Bundle args = new Bundle();
        Part1QuizQuestion part1QuizQuestion = (Part1QuizQuestion) question;
        args.putParcelable("question", part1QuizQuestion);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        if (args != null) {
            question = args.getParcelable("question");
            position = args.getInt("position");
        }
        binding = FragmentPart1QuizBinding.inflate(inflater, container, false);
        setupRadioButtons();
        setupSeekBar();
        setupPlayPauseButton();
        setupNextButton();
        return binding.getRoot();
    }

    private void setupRadioButtons() {
        trueAnswer = Integer.parseInt(question.getDapan());
        binding.radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = group.findViewById(checkedId);
            int selectID = checkedRadioButton.getId();
            if (selectID == R.id.radioButtonOptionA) {
                selectedAnswer = 1;
            } else if (selectID == R.id.radioButtonOptionB) {
                selectedAnswer = 2;
            } else if (selectID == R.id.radioButtonOptionC) {
                selectedAnswer = 3;
            } else if (selectID == R.id.radioButtonOptionD) {
                selectedAnswer = 4;
            }
            boolean result = selectedAnswer == trueAnswer;
            QuestionResult questionResult = new QuestionResult(result, selectedAnswer,true,position,trueAnswer);
            if (position < Objects.requireNonNull(quizSharedViewModel.questionResults.getValue()).size()) {
                quizSharedViewModel.updateQuestionResult(position, questionResult);
            } else {
                quizSharedViewModel.addQuestionResult(questionResult);
            }
            Objects.requireNonNull(quizSharedViewModel.answeredQuestions.getValue()).add(position);
        });
        binding.radioButtonOptionA.setText(question.getA());
        binding.radioButtonOptionB.setText(question.getB());
        binding.radioButtonOptionC.setText(question.getC());
        binding.radioButtonOptionD.setText(question.getD());
    }

    private void setupSeekBar() {
        final Runnable updateSeekBar = new Runnable() {
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(updateSeekBar, 1000);
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    if(mediaPlayer != null){
                        mediaPlayer.seekTo(progress);
                        binding.seekBar.setProgress(progress);
                    }
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying())
                    mediaPlayer.pause();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer != null && !mediaPlayer.isPlaying())
                    mediaPlayer.start();
            }
        });
    }

    private void setupPlayPauseButton() {
        Part1QuizQuestion part1QuizQuestion = (Part1QuizQuestion) question;
        Future<File> future = CacheManager.cacheImageFile(getContext(), part1QuizQuestion.getAnh());
        try{
            File cacheFile = future.get();
            Glide.with(this).load(cacheFile).into(binding.imageView2);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        binding.buttonPlayPause.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
                try {
                    String mp3Url =  BuildConfig.BASE_URL + part1QuizQuestion.getAmthanh();
                    Log.i("QuizPart1Fragment", "setupPlayPauseButton: " + mp3Url);
                    Future<File> mp3File = CacheManager.cacheMp3File(getContext(), mp3Url);
                    File cacheFile = mp3File.get();
                    mediaPlayer.setDataSource(cacheFile.getPath());
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer.start();
                binding.buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                mediaPlayer.setOnCompletionListener(mp -> binding.buttonPlayPause.setImageResource(android.R.drawable.ic_media_play));
            } else if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                binding.buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mediaPlayer.start();
                binding.buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            }
            binding.seekBar.setMax(mediaPlayer.getDuration());
        });
    }

    private void setupNextButton() {
        if (position == 0) {
            binding.buttonBack.setVisibility(View.INVISIBLE);
        }
        if (position == Objects.requireNonNull(quizViewModel.quizQuestions.getValue()).size() - 1) {
            binding.buttonNext.setText("Finish");
            binding.buttonNext.setOnClickListener(v -> {
                quizSharedViewModel.calculateScore();
                quizViewModel.isFragmentVisible.set(false);
                PracticeResultFragment practiceResultFragment = new PracticeResultFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.result_fragment_container, practiceResultFragment)
                        .commit();
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopMediaPlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releaseMediaPlayer();
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            //set binding.seekBar.setProgress(0);
            binding.seekBar.setProgress(0);
            binding.buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
            mediaPlayer = null;
        }
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}