package com.example.englishapp.presentation.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.domain.GetPart5QuestionUseCase;

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

    public MutableLiveData<ArrayList<Part5QuizQuestion>> part5QuizQuestions = new MutableLiveData<>();
    private ArrayList<String> userAnswers = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);
    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);
    public void setUserAnswer(int questionNumber, String answer) {
        // Make sure the ArrayList is big enough to store the answer
        while (userAnswers.size() <= questionNumber) {
            userAnswers.add(null);
        }

        userAnswers.set(questionNumber, answer);
    }
    public String getUserAnswer(int questionNumber) {
        if (questionNumber < userAnswers.size()) {
            return userAnswers.get(questionNumber);
        } else {
            return null;
        }
    }
    public int calculateScore() {
        int score = 0;
        for (int i = 0; i < part5QuizQuestions.getValue().size(); i++) {
            Part5QuizQuestion question = part5QuizQuestions.getValue().get(i);
            String userAnswer = getUserAnswer(i);
            if (question.getDapan().equals(userAnswer)) {
                score++;
            }
        }
        return score;
    }
    @Inject
    public QuizViewModel(GetPart5QuestionUseCase getPart5QuestionUseCase) {
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        getPart5Questions();
    }

    public void getPart5Questions(){
        isLoading.set(true);
        Disposable disposable = getPart5QuestionUseCase.execute(5)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(part5QuizQuestionModel -> {
                    if (part5QuizQuestionModel.isSuccess()) {
                        isLoading.set(false);
                        part5QuizQuestions.setValue(part5QuizQuestionModel.getResult());
                    }
                }, throwable -> {
                    isLoading.set(false);
                });

        compositeDisposable.add(disposable);
    }





}
