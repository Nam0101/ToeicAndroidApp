package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginViewModel extends ViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final UserRepository repository;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public LoginViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void login(String username, String password) {
        compositeDisposable.add(repository.getUser(username, password)
                .subscribe(userModel -> {
                    if(userModel.isSuccess()){
                        Log.i("LoginViewModel", "login: " + userModel);
                        Log.i("LoginViewModel", "login: " + userModel.getResult().get(0).getEmail());
                    }else {
                        Log.i("LoginViewModel", "login: " + userModel);
                    }
                }
                , throwable -> {
                            Log.e("LoginViewModel", "login: " + throwable.getMessage());
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
