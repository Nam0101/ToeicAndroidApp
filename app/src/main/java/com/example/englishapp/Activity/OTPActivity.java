package com.example.englishapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;
import com.example.englishapp.Retrofit.ApiApp;
import com.example.englishapp.Retrofit.RetrofitClient;
import com.example.englishapp.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OTPActivity extends AppCompatActivity {

    Button verifyBtn;
    TextView otpEmail,otpMobile;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiApp apiApp;

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.length() > 0){
                if(selectedETPosition == 0){
                    selectedETPosition = 1;
                    showKeyboard(otpEt2);
                } else if (selectedETPosition == 1) {
                    selectedETPosition = 2;
                    showKeyboard(otpEt3);
                } else if (selectedETPosition == 2) {
                    selectedETPosition = 3;
                    showKeyboard(otpEt4);
                }
            }

        }
    };
    private EditText otpEt1, otpEt2, otpEt3, otpEt4;
    private TextView resendBtn;
    private boolean resendEnable = false;
    private int resendTime = 60;
    String getEmail,getMobile;

    private int selectedETPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitView();
        InitControl();


    }

    private void InitControl() {

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myString");

        String gmail = bundle.getString("email_quenpass");
        String mobile = bundle.getString("mobile_quenpass");

        otpEmail.setText(gmail);
        otpMobile.setText(mobile);

        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        showKeyboard(otpEt1);
        startCountDownTimer();

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resendEnable){
                    startCountDownTimer();
                }
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strOTP = otpEt1.getText().toString()+otpEt2.getText().toString()+otpEt3.getText().toString()+otpEt4.getText().toString();

                if(strOTP.length() == 4){

                    compositeDisposable.add(apiApp.xacthucma(gmail,strOTP)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if (userModel.isSuccess()){
                                            Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                            Intent intent1 = new Intent(getApplicationContext(),NewPassWordActivity.class);
                                            Bundle bundle1 = new Bundle();
                                            bundle1.putString("stringCode",strOTP);
                                            bundle1.putString("stringGmail",gmail);
                                            bundle1.putString("stringMobile",mobile);
                                            intent1.putExtra("myPackage",bundle1);
                                            startActivity(intent1);
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));


                }else {
                    Toast.makeText(getApplicationContext(), "Bạn hãy nhập mã nhận được", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void InitView() {
        setContentView(R.layout.activity_otpactivity);
        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);
        otpEt1 = findViewById(R.id.otpET1);
        otpEt2 = findViewById(R.id.otpET2);
        otpEt3 = findViewById(R.id.otpET3);
        otpEt4 = findViewById(R.id.otpET4);
        verifyBtn = findViewById(R.id.verifyBtn);
        otpEmail = findViewById(R.id.otpEmail);
        otpMobile = findViewById(R.id.otpMobile);
        resendBtn = findViewById(R.id.recendBtn);
    }

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer(){
        resendEnable = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));
        new CountDownTimer(resendTime * 1000, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                resendBtn.setText("Resend Code("+(millisUntilFinished / 1000)+")");
            }

            @Override
            public void onFinish(){
                resendEnable = true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }


    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_DEL){
            if(selectedETPosition == 3){

                selectedETPosition = 2;
                showKeyboard(otpEt3);

            } else if (selectedETPosition == 2) {

                selectedETPosition = 1;
                showKeyboard(otpEt2);

            } else if (selectedETPosition == 1){
                selectedETPosition = 0;
                showKeyboard(otpEt1);
            }

            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}