package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuestionResult;
import com.example.englishapp.domain.InsertExamHistoryUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class QuizSharedViewModel extends ViewModel {
    public final MutableLiveData<List<QuestionResult>> questionResults = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<Integer> numberOfQuestion = new MutableLiveData<>(0);
    public ObservableField<String> score = new ObservableField<>();
    public ObservableField<String> points = new ObservableField<>();
    public final MutableLiveData<List<Integer>> answeredQuestions = new MutableLiveData<>(new ArrayList<>());
    public int examId = 0;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final InsertExamHistoryUseCase insertExamHistoryUseCase;
    @Inject
    public QuizSharedViewModel(InsertExamHistoryUseCase insertExamHistoryUseCase) {
        this.insertExamHistoryUseCase = insertExamHistoryUseCase;
    }

    public void addQuestionResult(QuestionResult result) {
        List<QuestionResult> currentResults = questionResults.getValue();
        assert currentResults != null;
        currentResults.add(result);
        questionResults.setValue(currentResults);

    }

    public void calculateScore() {
        int score = 0;
        int points = 0;
        for (QuestionResult result : Objects.requireNonNull(questionResults.getValue())) {
            if(result.isCorrect()){
                score++;
                points += 10;
            }
        }
        this.score.set(String.valueOf(score));
        this.points.set(String.valueOf(points));
    }

    public void updateQuestionResult(int position, QuestionResult newResult) {
        if (position >= 0 && position < Objects.requireNonNull(questionResults.getValue()).size()) {
            questionResults.getValue().set(position, newResult);
            questionResults.setValue(questionResults.getValue()); // Trigger observers
        }
    }
    public void insertExamHistory(int user_id, int exam_id, int correct_answer, int total_question, String exam_date) {
        Disposable disposable = insertExamHistoryUseCase.execute(user_id, exam_id, correct_answer, total_question, exam_date)
                 .subscribeOn(Schedulers.io())
                    .subscribe(
                            examHistoryResponse -> {
                                examId = examHistoryResponse.getResult().getExam_id();
                            },
                            throwable -> {
                                Log.i("PracticeResultFragment", "insertExamHistory: " + throwable.getMessage());
                            }
                    );

        compositeDisposable.add(disposable);
    }
}