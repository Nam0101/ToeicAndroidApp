package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.LoginUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
@HiltViewModel
public class LoginViewModel extends ViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final LoginUseCase loginUseCase;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);

    public final ObservableField<String> savedUsername = new ObservableField<>();
    public final ObservableField<String> savedPassword = new ObservableField<>();
    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }
    public void login(String username, String password) {
        savedUsername.set(username);
        savedPassword.set(password);
        loginUseCase.execute(username, password)
                .subscribe(userModel -> {
                    if(userModel.isSuccess()){
                        Log.i("LoginViewModel", "login: " + userModel);
                        Log.i("LoginViewModel", "login: " + userModel.getResult().get(0).getUsername());
                    }else {
                        Log.i("LoginViewModel", "login: " + userModel);
                    }
                }
                , throwable -> {
                            Log.e("LoginViewModel", "login: " + throwable.getMessage());
                });

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
