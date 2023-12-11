package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuizQuestion;
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

    public MutableLiveData<ArrayList<? extends QuizQuestion>> part5QuizQuestions = new MutableLiveData<>();
    private ArrayList<String> userAnswers = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);
    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);


    @Inject
    public QuizViewModel(GetPart5QuestionUseCase getPart5QuestionUseCase) {
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        getPart5Questions();
    }

    public void getPart5Questions(){
        isLoading.set(true);
        Disposable disposable = getPart5QuestionUseCase.execute(30)
                .subscribeOn(Schedulers.io()) // network call on IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizQuestionModel -> {
                    isLoading.set(false);
                    if (quizQuestionModel.isSuccess()) {
                        part5QuizQuestions.setValue(quizQuestionModel.getResult());
                    }
                }, throwable -> {
                    isLoading.set(false);
                    Log.i("QuizViewModel", "getPart5Questions: " + throwable.getMessage());
                });

        compositeDisposable.add(disposable);
    }

}
