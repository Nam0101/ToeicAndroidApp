package com.example.englishapp.presentation.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.ResetPasswordUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@HiltViewModel
public class NewPasswordActivityViewModel extends ViewModel {
    private final ResetPasswordUseCase resetPasswordUseCase;
    public ObservableField<String> newPassword = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<String> code = new ObservableField<>();
    public ObservableField<Boolean> isPasswordShowing = new ObservableField<>(false);
    public ObservableField<Boolean> isConfirmPasswordShowing = new ObservableField<>(false);
    public MutableLiveData<Boolean> isNavigateToSignIn = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> message = new MutableLiveData<>();

    @Inject
    public NewPasswordActivityViewModel(ResetPasswordUseCase resetPasswordUseCase) {
        this.resetPasswordUseCase = resetPasswordUseCase;
    }
    public void onClickHidePass() {
        isPasswordShowing.set(Boolean.FALSE.equals(isPasswordShowing.get()));
    }
    public void onClickHideConfirmPass() {
        isConfirmPasswordShowing.set(Boolean.FALSE.equals(isConfirmPasswordShowing.get()));
    }
    public void onClickSignIn(){
        isNavigateToSignIn.setValue(true);
    }
    public void changePassword(){
        String newPassword = this.newPassword.get();
        String confirmPassword = this.confirmPassword.get();
        String email = this.email.get();
        String mobile = this.mobile.get();
        String code = this.code.get();
        if(newPassword.equals(confirmPassword)){
            compositeDisposable.add(resetPasswordUseCase.execute(email,mobile,code,newPassword)
                    .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                    .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe(userModel -> {
                        if(userModel.isSuccess()){
                            isNavigateToSignIn.setValue(true);
                            message.setValue(userModel.getMessage());
                        }
                    }, throwable -> {
                        message.setValue(throwable.getMessage());
                    }));
        }
        else{
            //show message
            message.setValue("Mật khẩu không trùng khớp");
        }
    }
}
