package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.repository.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
@HiltViewModel
public class LoginViewModel extends ViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final UserRepository repository;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);

    public final ObservableField<String> savedUsername = new ObservableField<>();
    public final ObservableField<String> savedPassword = new ObservableField<>();
    @Inject
    public LoginViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void login(String username, String password) {
        savedUsername.set(username);
        savedPassword.set(password);
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

    public void onClickHidePass() {
        isPasswordShowing.set(Boolean.FALSE.equals(isPasswordShowing.get()));
    }
    public void onClickSignIn() {
        login(email.get(), password.get());
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }


}
