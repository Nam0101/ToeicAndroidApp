package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.Topic;
import com.example.englishapp.data.model.Vocabulary;
import com.example.englishapp.domain.GetTopicUseCase;
import com.example.englishapp.domain.GetVocabularyByTopicUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class VocabularyViewModel extends ViewModel {
    public final GetTopicUseCase getTopicUseCase;
    public final GetVocabularyByTopicUseCase getVocabularyByTopicUseCase;

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);
    public ObservableField<Boolean> isViewPagger = new ObservableField<>(false);
    public MutableLiveData<ArrayList<Topic>> topics = new MutableLiveData<>();
    public MutableLiveData<List<Vocabulary>> vocabularies = new MutableLiveData<>();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public VocabularyViewModel(GetTopicUseCase getTopicUseCase, GetVocabularyByTopicUseCase getVocabularyByTopicUseCase) {
        this.getTopicUseCase = getTopicUseCase;
        this.getVocabularyByTopicUseCase = getVocabularyByTopicUseCase;
        getTopic();
    }

    public void getTopic() {
        isLoading.set(true);
        Disposable disposable = getTopicUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topicModel -> {
                    if (topicModel.isSuccess()) {
                        topics.setValue(topicModel.getResult());
                        isLoading.set(false);
                        Log.i("VocabularyViewModel", "getTopic: " + topicModel.toString());

                    }
                    else {
                        Log.i("VocabularyViewModel", "getTopic: " + topicModel.getResult());
                    }
                });
        compositeDisposable.add(disposable);
    }
    public void getVocabularyByTopic(int topic_id) {
        isLoading.set(true);
        Disposable disposable = getVocabularyByTopicUseCase.execute(topic_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vocabularyModel -> {
                    if (vocabularyModel.isSuccess()) {
                        Log.i("VocabularyViewModel", "getVocabularyByTopic: " + vocabularyModel.toString());
                        isLoading.set(false);
                        vocabularies.setValue(vocabularyModel.getResult());
                        isViewPagger.set(true);


                    }
                    else {
                        Log.i("VocabularyViewModel", "getVocabularyByTopic: " + vocabularyModel.getResult());
                    }
                });
        compositeDisposable.add(disposable);
    }
}
