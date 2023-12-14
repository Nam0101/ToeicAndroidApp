package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.domain.Functions;
import com.example.englishapp.data.model.User;
import com.example.englishapp.domain.GetFunctionsUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Function>> functions = new MutableLiveData<>();
    public final GetFunctionsUseCase getFunctionsUseCase;

    public ObservableField<Boolean> isFragmentVisible = new ObservableField<>(false);

    public User user;
    public ObservableField<String> hello = new ObservableField<>();

    public ObservableField<Boolean> isPart1Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart2Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart3Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart4Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart5Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart6Checked = new ObservableField<>(false);
    public ObservableField<Boolean> isPart7Checked = new ObservableField<>(false);

    public MutableLiveData<ArrayList<Integer>> selectedParts = new MutableLiveData<>();

    @Inject
    public MainActivityViewModel(GetFunctionsUseCase getFunctionsUseCase) {
        this.getFunctionsUseCase = getFunctionsUseCase;
        getFunctions();
        Log.i("MainActivityViewModel", "MainActivityViewModel created!");
    }
    public void setUser(User user){
        this.user = user;
        this.hello.set("Hello " + user.getUsername());
    }

    public void getFunctions(){
        functions.setValue(getFunctionsUseCase.execute());
        Functions.getInstance().setFunctions(functions.getValue());
    }
    public void startTest(){
        ArrayList<Integer> selectedParts = new ArrayList<>();
        if (Boolean.TRUE.equals(isPart1Checked.get())){
            selectedParts.add(1);
        }
        if (Boolean.TRUE.equals(isPart2Checked.get())){
            selectedParts.add(2);
        }
        if (Boolean.TRUE.equals(isPart3Checked.get())){
            selectedParts.add(3);
        }
        if (Boolean.TRUE.equals(isPart4Checked.get())){
            selectedParts.add(4);
        }
        if (Boolean.TRUE.equals(isPart5Checked.get())){
            selectedParts.add(5);
        }
        if (Boolean.TRUE.equals(isPart6Checked.get())){
            selectedParts.add(6);
        }
        if (Boolean.TRUE.equals(isPart7Checked.get()))
        {
            selectedParts.add(7);
        }
        this.selectedParts.setValue(selectedParts);
    }

}