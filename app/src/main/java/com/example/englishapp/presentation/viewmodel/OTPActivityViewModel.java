package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.OTPVerifyUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class OTPActivityViewModel extends ViewModel {
    private final OTPVerifyUseCase otpVerifyUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    //message

    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSucess = new MutableLiveData<>();
    //mobie
    public ObservableField<String> mobile = new ObservableField<>();
    //email
    public ObservableField<String> email =  new ObservableField<>();
    public ObservableField<String> code1 = new ObservableField<>("");
    public ObservableField<String> code2 = new ObservableField<>("");
    public ObservableField<String> code3 = new ObservableField<>("");
    public ObservableField<String> code4 = new ObservableField<>("");

    @Inject
    public OTPActivityViewModel(OTPVerifyUseCase otpVerifyUseCase, ForgotPasswordUseCase forgotPasswordUseCase) {
        this.otpVerifyUseCase = otpVerifyUseCase;
        this.forgotPasswordUseCase = forgotPasswordUseCase;
    }

    public void verifyOTP(){
        String code = code1.get() + code2.get() + code3.get() + code4.get();
        String email = this.email.get();
        Log.i("OTPActivityViewModel", "verifyOTP: " + code);
        compositeDisposable.add(otpVerifyUseCase.execute(email, code)
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(userModel -> {
                    if(userModel.isSuccess()){
                        isSucess.setValue(true);
                    }
                    else {
                        isSucess.setValue(false);
                        message.setValue(userModel.getMessage());
                    }
                }, throwable -> {
                    isSucess.setValue(false);
                    message.setValue(throwable.getMessage());
                }));
    }
    public void resendOTP(){
        message.setValue("Resend OTP");
        Disposable disposable = forgotPasswordUseCase.execute(email.get(), mobile.get())
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(userModel -> {
                            isSucess.setValue(userModel.isSuccess());
                            message.setValue(userModel.getMessage());
                            Log.i("ForgotPasswordViewModel", "onCreate: " + userModel.isSuccess());
                        }
                        , throwable -> {
                            isSucess.setValue(false);
                            message.setValue(throwable.getMessage());
                        });


        compositeDisposable.add(disposable);
    }

}
