package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.databinding.adapters.NumberPickerBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.Exam;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.domain.GetListExamUseCase;
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
public class ExamViewModel extends ViewModel {
    private final GetListExamUseCase getListExamUseCase;
    private final GetPart1QuestionUseCase getPart1QuestionUseCase;
    private final GetPart2QuestionUseCase getPart2QuestionUseCase;
    private final GetPart3QuestionUseCase getPart3QuestionUseCase;
    private final GetPart4QuestionUseCase getPart4QuestionUseCase;
    private final GetPart5QuestionUseCase getPart5QuestionUseCase;
    private final GetPart6QuestionUseCase getPart6QuestionUseCase;
    private final GetPart7QuestionUseCase getPart7QuestionUseCase;
    public MutableLiveData<ArrayList<Exam>> examList = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public MutableLiveData<ArrayList<? extends QuizQuestion>> quizQuestions = new MutableLiveData<>();
    public ObservableField<String> countdownTime = new ObservableField<>("Seconds remaining: 60");

    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);
    public MutableLiveData<String> errmsg = new MutableLiveData<>();
    public MutableLiveData<Boolean> isQuizTimmerFinished = new MutableLiveData<>(false);
    public NumberPickerBindingAdapter numberOfQuestion;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public int examId = 0;

    @Inject
    public ExamViewModel(GetListExamUseCase getListExamUseCase,
                         GetPart1QuestionUseCase getPart1QuestionUseCase,
                         GetPart2QuestionUseCase getPart2QuestionUseCase,
                         GetPart3QuestionUseCase getPart3QuestionUseCase,
                         GetPart4QuestionUseCase getPart4QuestionUseCase,
                         GetPart5QuestionUseCase getPart5QuestionUseCase,
                         GetPart6QuestionUseCase getPart6QuestionUseCase,
                         GetPart7QuestionUseCase getPart7QuestionUseCase) {
        this.getListExamUseCase = getListExamUseCase;
        this.getPart1QuestionUseCase = getPart1QuestionUseCase;
        this.getPart2QuestionUseCase = getPart2QuestionUseCase;
        this.getPart3QuestionUseCase = getPart3QuestionUseCase;
        this.getPart4QuestionUseCase = getPart4QuestionUseCase;
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        this.getPart6QuestionUseCase = getPart6QuestionUseCase;
        this.getPart7QuestionUseCase = getPart7QuestionUseCase;
        getExamList();
    }

    public void getQuestionByExamId(int examId) {
        this.examId = examId;
        isLoading.set(true);
        getPart1Questions(examId);
        getPart2Questions(examId);
        getPart3Questions(examId);
        getPart4Questions(examId);
        getPart5Questions(examId);
        getPart6Questions(examId);
        getPart7Questions(examId);
}

private void getPart1Questions(int examId) {
    Disposable disposable = getPart1QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}

private void getPart2Questions(int examId) {
    Disposable disposable = getPart2QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}
private void getPart3Questions(int examId) {
    Disposable disposable = getPart3QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}
private void getPart4Questions(int examId) {
    Disposable disposable = getPart4QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}
private void getPart5Questions(int examId) {
    Disposable disposable = getPart5QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}
private void getPart6Questions(int examId) {
    Disposable disposable = getPart6QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}
private void getPart7Questions(int examId) {
    Disposable disposable = getPart7QuestionUseCase.executeByExamId(examId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSuccess, this::handleError);
    compositeDisposable.add(disposable);
}

private void handleSuccess(QuizQuestionModel quizQuestionModel) {
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

private void handleError(Throwable throwable) {
    Log.i("ExamViewModel", "getQuestionByExamId: " + throwable.getMessage());
    isLoading.set(false);
    errmsg.postValue(throwable.getMessage());
}

    public void getExamList() {
        isLoading.set(true);
        Disposable disposable = getListExamUseCase.execute()
                .subscribeOn(Schedulers.io()) // perform network request on IO thread
                .observeOn(AndroidSchedulers.mainThread()) // handle result on main thread
                .subscribe(exams -> {
                    if (exams.isSuccess()) {
                        ArrayList<Exam> examArrayList = new ArrayList<>(exams.getResult());
                        examList.postValue(examArrayList);
                        isLoading.set(false);
                    }
                }, throwable -> {
                    Log.i("ExamViewModel", "getExamList: " + throwable.getMessage());
                    isLoading.set(false);
                });
        compositeDisposable.add(disposable);
    }


}
