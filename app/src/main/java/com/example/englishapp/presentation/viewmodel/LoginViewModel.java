package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.User;
import com.example.englishapp.domain.LoginUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final LoginUseCase loginUseCase;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);
    public final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    public final ObservableField<String> savedUsername = new ObservableField<>();
    public final ObservableField<String> savedPassword = new ObservableField<>();
    public final MutableLiveData<User> loggedInUser = new MutableLiveData<>();
    public final MutableLiveData<Void> navigateToSignUp = new MutableLiveData<>();
    public final MutableLiveData<Void> navigateToForgotPassword = new MutableLiveData<>();
    public final MutableLiveData<String> loginErrorMessage = new MutableLiveData<>();

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public void login(String username, String password) {
        savedUsername.set(username);
        savedPassword.set(password);
        Disposable disposable = loginUseCase.execute(username, password)
                .subscribe(userModel -> {
                            if (userModel.isSuccess()) {
                                loginSuccess.setValue(true);
                                loggedInUser.setValue(userModel.getResult().get(0));
                            } else {
                                loginSuccess.setValue(false);
                                loginErrorMessage.setValue(userModel.getMessage());
                            }
                        }
                        , throwable -> {
                            Log.e("LoginViewModel", "login: " + throwable.getMessage());
                            loginErrorMessage.setValue(throwable.getMessage());
                    });

        compositeDisposable.add(disposable);

    }

    public void onClickHidePass() {
        isPasswordShowing.set(Boolean.FALSE.equals(isPasswordShowing.get()));
    }
    public void onClickSignIn() {
        login(email.get(), password.get());
    }

    public void onClickForgotPass() {
        navigateToForgotPassword.setValue(null);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void onClickSignUp() {
        navigateToSignUp.setValue(null);
    }



}
