package com.example.englishapp.presentation.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.model.User;
import com.example.englishapp.domain.GetFunctionsUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Function>> functions = new MutableLiveData<>();
    public final GetFunctionsUseCase getFunctionsUseCase;

    private User user;
    public ObservableField<String> hello = new ObservableField<>();

    @Inject
    public MainActivityViewModel(GetFunctionsUseCase getFunctionsUseCase) {
        this.getFunctionsUseCase = getFunctionsUseCase;
        getFunctions();
    }
    public void setUser(User user){
        this.user = user;
        this.hello.set("Hello " + user.getUsername());
    }

    public void getFunctions(){
        functions.setValue(getFunctionsUseCase.execute());
    }
}