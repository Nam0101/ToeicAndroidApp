package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.ForgotPasswordUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class ForgotPasswordViewModel  extends ViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSucess = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    @Inject
    public ForgotPasswordViewModel(ForgotPasswordUseCase forgotPasswordUseCase) {
        this.forgotPasswordUseCase = forgotPasswordUseCase;
    }

    public void onclickSearch(){
        //check if email or mobile is empty
        if (email.get() == null || email.get().isEmpty()){
            message.setValue("Please enter email");
            return;
        }
        if (mobile.get() == null || mobile.get().isEmpty()){
            message.setValue("Please enter mobile");
            return;
        }
        isLoading.setValue(true);
        Disposable disposable = forgotPasswordUseCase.execute(email.get(), mobile.get())
                .doFinally(() -> isLoading.setValue(false))
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

    public void onClickSignIn(){
        isSucess.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

