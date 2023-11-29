package com.example.englishapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
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

public class LoginActivity extends AppCompatActivity {

    TextView SignUpbtn,btnForgotPass;
    EditText edtGmailSignIn,edtPassword;
    ImageView IconPasshide;
    AppCompatButton btnSignIn;
    private boolean PasswordShowing = false;
    ApiApp apiApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initControl();

    }

    private void initControl() {

        btnForgotPass.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
            startActivity(intent);
        });
        SignUpbtn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,RegisterActivity.class)));

        IconPasshide.setOnClickListener(view -> {
            if (PasswordShowing){
                PasswordShowing = false ;
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                IconPasshide.setImageResource(R.drawable.hide);

            }else {
                PasswordShowing = true ;

                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                IconPasshide.setImageResource(R.drawable.eye);
            }
            edtPassword.setSelection(edtPassword.length());
        });

        btnSignIn.setOnClickListener(view -> {

            String str_email = edtGmailSignIn.getText().toString().trim();
            String str_pass = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(str_email)) {
                Toast.makeText(getApplicationContext(), "Bạn hãy nhập email nhé!", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(str_pass)) {
                Toast.makeText(getApplicationContext(), "Bạn hãy nhập Mật khẩu nhé!", Toast.LENGTH_SHORT).show();
            }else{
                compositeDisposable.add(apiApp.dangnhap(str_email, str_pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if (userModel.isSuccess()) {
                                        // Login success
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        Log.i("TAG", "Login success: " + userModel.getMessage());
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Login failed, handle the error message
                                        Log.i("TAG", "Login failed: " + userModel.getMessage());
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                },
                                throwable -> {
                                    // Handle network or other errors
                                    Log.e("TAG", "Error during login", throwable);
                                    Toast.makeText(getApplicationContext(), "Error during login", Toast.LENGTH_LONG).show();
                                }
                        ));

            }

        });

    }

    private void initView() {
          apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);
          edtGmailSignIn = findViewById(R.id.edtGmailSignIn);
          edtPassword = findViewById(R.id.edtPassWord);
          SignUpbtn = findViewById(R.id.btnSigUp);
          IconPasshide = findViewById(R.id.IconPassHide);
          btnSignIn = findViewById(R.id.btnSignIn);
          btnForgotPass = findViewById(R.id.forgotPass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_curent.getEmail() != null && Utils.user_curent.getPass() != null){
            edtGmailSignIn.setText(Utils.user_curent.getEmail());
            edtPassword.setText(Utils.user_curent.getPass());


        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}