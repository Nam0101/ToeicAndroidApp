package com.example.englishapp.presentation.viewmodel;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class QuizViewModel extends ViewModel {

    private final GetPart5QuestionUseCase getPart5QuestionUseCase;
    private final GetPart6QuestionUseCase getPart6QuestionUseCase;

    public MutableLiveData<ArrayList<? extends QuizQuestion>> quizQuestions = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ObservableField<String> countdownTime = new ObservableField<>("Seconds remaining: 60");

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);
    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);
    public MutableLiveData<Boolean> isQuizTimmerFinished = new MutableLiveData<>(false);

    @Inject
    public QuizViewModel(GetPart5QuestionUseCase getPart5QuestionUseCase, GetPart6QuestionUseCase getPart6QuestionUseCase) {
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        this.getPart6QuestionUseCase = getPart6QuestionUseCase;

    }

    public void getPart5Questions(){
        isLoading.set(true);
        Disposable disposable = getPart5QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        quizQuestions.setValue(quizQuestionModel.getResult());
                        startTimer();
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart5Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }

    public void getPart6Questions(){
        isLoading.set(true);
        Disposable disposable = getPart6QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        quizQuestions.setValue(quizQuestionModel.getResult());
                        startTimer();
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart6Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }


    private void startTimer() {
        CountDownTimer timer = new CountDownTimer(1800000, 1000) {
            public void onTick(long millisUntilFinished) {
                String minute = String.valueOf((millisUntilFinished / 1000) / 60);
                String seconds = String.valueOf((millisUntilFinished / 1000) % 60);
                countdownTime.set(minute + ":" + seconds);
            }
            public void onFinish() {
                countdownTime.set("done!");
                isQuizTimmerFinished.setValue(true);
            }
        }.start();
    }

}
