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
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);
    public ObservableField<Boolean> isConfirmPasswordShowing = new ObservableField<>(false);
    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
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
        if (!checkValidInfo()){
            return;
        }
        isLoading.setValue(true);
        compositeDisposable.add(registerUseCase.execute(userName.get(), password.get(), email.get(), mobile.get())
                .subscribe(userModel -> {
                    navigateToLogin.setValue(null);
                    isLoading.setValue(false);
                }, throwable -> {
                    Log.i("TAG", "onClickSignUp: " + throwable.getMessage());
                    message.setValue(throwable.getMessage());
                }));
    }
    private Boolean checkValidInfo(){
        if (userName.get() == null || userName.get().isEmpty()){
            message.setValue("Please enter username");
            return false;
        }
        if (password.get() == null || password.get().isEmpty()){
            message.setValue("Please enter password");
            return false;
        }
        if (confirmPassword.get() == null || confirmPassword.get().isEmpty()){
            Log.i("TAG", "checkValidInfo: " + confirmPassword.get());
            message.setValue("Please enter confirm password");
            return false;
        }
        if (email.get() == null || email.get().isEmpty()){
            message.setValue("Please enter email");
            return false;
        }
        if (mobile.get() == null || mobile.get().isEmpty()){
            message.setValue("Please enter mobile");
            return false;
        }
        if (!password.get().equals(confirmPassword.get())){
            message.setValue("Password and confirm password not match");
            return false;
        }
        return true;
    }


}
