package com.example.englishapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.englishapp.R;
import com.example.englishapp.Retrofit.ApiApp;
import com.example.englishapp.Retrofit.RetrofitClient;
import com.example.englishapp.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText edtForgotPassGmail,edtForgotPassMobile;
    AppCompatButton btnForgotPassSearch;
    TextView btnForgotPassSignIn;
    ApiApp apiApp;
    ProgressBar progressBar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        initControl();
    }

    private void initControl() {
        btnForgotPassSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnForgotPassSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_email = edtForgotPassGmail.getText().toString().trim();
                String str_mobile = edtForgotPassMobile.getText().toString().trim();

                Bundle bundle = new Bundle();
                bundle.putString("email_quenpass",str_email);
                bundle.putString("mobile_quenpass",str_mobile);

                if (TextUtils.isEmpty(str_email)) {
                    Toast.makeText(getApplicationContext(), "Bạn hãy nhập email nhé!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(str_mobile)) {
                    Toast.makeText(getApplicationContext(), "Bạn hãy nhập số điện thoại nhé!", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    compositeDisposable.add(apiApp.quenmatkhau(str_email,str_mobile)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        progressBar.setVisibility(View.INVISIBLE); // Ẩn progressBar sau khi nhận được phản hồi
                                        if (userModel.isSuccess()) {
                                            Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                                            intent.putExtra("myString",bundle);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // Nếu không thành công, in ra thông báo lỗi từ server
                                            Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    },
                                    throwable -> {
                                        // Xử lý lỗi khi không thể kết nối đến server hoặc các lỗi khác
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE); // Ẩn progressBar khi có lỗi
                                    }
                            ));
                }
            }
        });
    }

    private void initView() {
        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);
        edtForgotPassGmail = findViewById(R.id.edtGmailForgot);
        edtForgotPassMobile = findViewById(R.id.edtPhoneNumberForgot);
        btnForgotPassSearch = findViewById(R.id.btnSearchForgot);
        btnForgotPassSignIn= findViewById(R.id.btnSignInForgotpass);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}