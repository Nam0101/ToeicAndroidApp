package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.RegisterUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
@HiltViewModel
public class RegisterViewModel extends ViewModel {


    private final MutableLiveData<Void> navigateToLogin = new MutableLiveData<>();

    public LiveData<Void> getNavigateToLogin() {
        return navigateToLogin;
    }
    private final RegisterUseCase registerUseCase;

    @Inject
    public RegisterViewModel(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    public  CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);
    public ObservableField<Boolean> isConfirmPasswordShowing = new ObservableField<>(false);
    public ObservableField<String> confirmPassword = new ObservableField<>();

    public void onClickHidePass() {
        isPasswordShowing.set(Boolean.FALSE.equals(isPasswordShowing.get()));
    }

    public void onClickHideConfirmPass() {
        isConfirmPasswordShowing.set(Boolean.FALSE.equals(isConfirmPasswordShowing.get()));
    }

    public void onClickSignIn() {
        navigateToLogin.setValue(null);
    }

    public void onClickSignUp() {
        compositeDisposable.add(registerUseCase.execute(userName.get(), password.get(), email.get(), mobile.get())
                .subscribe(userModel -> {
                    navigateToLogin.setValue(null);
                }, throwable -> {
                    Log.i("TAG", "onClickSignUp: " + throwable.getMessage());
                }));
    }



}
