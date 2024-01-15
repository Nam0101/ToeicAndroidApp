package com.example.englishapp.presentation.viewmodel;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.domain.GetPart1QuestionUseCase;
import com.example.englishapp.domain.GetPart2QuestionUseCase;
import com.example.englishapp.domain.GetPart3QuestionUseCase;
import com.example.englishapp.domain.GetPart4QuestionUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;
import com.example.englishapp.domain.GetPart7QuestionUseCase;

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
    private final GetPart2QuestionUseCase getPart2QuestionUseCase;
    private final GetPart4QuestionUseCase getPart4QuestionUseCase;
    private final GetPart6QuestionUseCase getPart6QuestionUseCase;
    private final GetPart7QuestionUseCase getPart7QuestionUseCase;
    private final GetPart1QuestionUseCase getPart1QuestionUseCase;
    private final GetPart3QuestionUseCase getPart3QuestionUseCase;
    public MutableLiveData<ArrayList<? extends QuizQuestion>> quizQuestions = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ObservableField<String> countdownTime = new ObservableField<>("Seconds remaining: 60");

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);
    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);
    public MutableLiveData<Boolean> isQuizTimmerFinished = new MutableLiveData<>(false);

    @Inject
    public QuizViewModel(GetPart5QuestionUseCase getPart5QuestionUseCase, GetPart6QuestionUseCase getPart6QuestionUseCase, GetPart7QuestionUseCase getPart7QuestionUseCase, GetPart1QuestionUseCase getPart1QuestionUseCase, GetPart3QuestionUseCase getPart3QuestionUseCase, GetPart2QuestionUseCase getPart2QuestionUseCase, GetPart4QuestionUseCase getPart4QuestionUseCase) {
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        this.getPart6QuestionUseCase = getPart6QuestionUseCase;
        this.getPart7QuestionUseCase = getPart7QuestionUseCase;
        this.getPart1QuestionUseCase = getPart1QuestionUseCase;
        this.getPart3QuestionUseCase = getPart3QuestionUseCase;
        this.getPart2QuestionUseCase = getPart2QuestionUseCase;
        this.getPart4QuestionUseCase = getPart4QuestionUseCase;
    }

    public void getPart5Questions(){
        isLoading.set(true);
        Disposable disposable = getPart5QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        if (quizQuestionModel.isSuccess()) {
                            ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                            if (currentQuestions != null) {
                                currentQuestions.addAll(quizQuestionModel.getResult());
                            } else {
                                currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                            }
                            quizQuestions.setValue(currentQuestions);
                        }
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart5Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }
    public void getPart4Questions(){
        isLoading.set(true);
        Disposable disposable = getPart4QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            currentQuestions.addAll(quizQuestionModel.getResult());
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart4Questions: " + throwable.getMessage());
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
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            for(QuizQuestion question : quizQuestionModel.getResult()){
                                currentQuestions.add(question);
                            }
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart6Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }

    public void getPart7Questions() {
        isLoading.set(true);
        Disposable disposable = getPart7QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            currentQuestions.addAll(quizQuestionModel.getResult());
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                });

        compositeDisposable.add(disposable);
    }

    public void startTimer(int time) {
        if(time == 0) time = 30 * 60 * 1000;
        CountDownTimer timer = new CountDownTimer(time, 1000) {
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

    public void getQuizQuestions(int part) {
        Log.i("QuizViewModel", "getQuizQuestions: " + part);
        switch (part) {
            case 1:
                getPart1Questions();
                break;
            case 2:
                getPart2Questions();
                break;
            case 4:
                getPart4Questions();
                break;
            case 3:
                getPart3Question();
                break;
            case 5:
                getPart5Questions();
                break;
            case 6:
                getPart6Questions();
                break;
            case 7:
                getPart7Questions();
                break;
        }
    }

    private void getPart2Questions() {
        isLoading.set(true);
        Disposable disposable = getPart2QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            currentQuestions.addAll(quizQuestionModel.getResult());
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart2Questions: " + throwable.getMessage());
                });
        compositeDisposable.add(disposable);
    }

    private void getPart3Question() {
        isLoading.set(true);
        Disposable disposable = getPart3QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            currentQuestions.addAll(quizQuestionModel.getResult());
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart3Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }

    public void getPart1Questions() {
        isLoading.set(true);
        Disposable disposable = getPart1QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        ArrayList<QuizQuestion> currentQuestions = (ArrayList<QuizQuestion>) quizQuestions.getValue();
                        if (currentQuestions != null) {
                            currentQuestions.addAll(quizQuestionModel.getResult());
                        } else {
                            currentQuestions = new ArrayList<>(quizQuestionModel.getResult());
                        }
                        quizQuestions.setValue(currentQuestions);
                    }
                }, throwable -> {
                    isLoading.set(false);
                });

        compositeDisposable.add(disposable);
    }
}
