package com.example.englishapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
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

public class RegisterActivity extends AppCompatActivity {

    EditText edtuserName, edtGmail, edtMobile, edtPasswordSignup, edtConPasswordSignup;
    TextView btnSignIndangki;
    AppCompatButton btnSignUp;
    ImageView IconUpPass,IconUpConPass;
    ApiApp apiApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean passwordShowing = false;
    private boolean passwordConShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initControl();

    }

    private void initView() {

        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);

        edtGmail = findViewById(R.id.edtGmail);
        edtMobile = findViewById(R.id.edtPhoneNumber);
        edtuserName = findViewById(R.id.edtuserNameDangki);
        edtPasswordSignup = findViewById(R.id.edtPassWordSigUp);
        edtConPasswordSignup = findViewById(R.id.edtConPassWordSigUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIndangki = findViewById(R.id.btnSignInUp);

        IconUpPass = findViewById(R.id.IconPassHideUp);
        IconUpConPass = findViewById(R.id.IconConPassHideUp);

    }

    private void initControl() {

        IconUpPass.setOnClickListener(view -> {
            if (passwordShowing){
                passwordShowing = false ;
                edtPasswordSignup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                IconUpPass.setImageResource(R.drawable.hide);
            }else {
                passwordShowing = true ;
                edtPasswordSignup.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                IconUpPass.setImageResource(R.drawable.eye);
            }
            edtPasswordSignup.setSelection(edtPasswordSignup.length());
        });

        IconUpConPass.setOnClickListener(view -> {

            if (passwordConShowing){
                passwordConShowing = false ;
                edtConPasswordSignup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                IconUpConPass.setImageResource(R.drawable.hide);

            }else {
                passwordConShowing = true ;
                edtConPasswordSignup.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                IconUpConPass.setImageResource(R.drawable.eye);
            }
            edtConPasswordSignup.setSelection(edtConPasswordSignup.length());
        });

        btnSignUp.setOnClickListener(view -> dangKi());

        btnSignIndangki.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));

    }

    private void dangKi() {
        String str_username = edtuserName.getText().toString().trim();
        String str_email = edtGmail.getText().toString().trim();
        String str_pass = edtPasswordSignup.getText().toString().trim();
        String str_repass = edtConPasswordSignup.getText().toString().trim();
        String str_mobile = edtMobile.getText().toString().trim();


        if (TextUtils.isEmpty(str_username)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập tên nhé!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_email)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập email nhé!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_mobile)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập số điện thoại nhé!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_pass)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập mật khẩu nhé! ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập  xác nhận mật khẩu nhé! ", Toast.LENGTH_SHORT).show();
        } else {
            if(str_pass.equals(str_repass)){

                compositeDisposable.add(apiApp.dangki(str_email, str_pass, str_username, str_mobile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Utils.user_curent.setEmail(str_email);
                                        Utils.user_curent.setPass(str_pass);
                                        Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(),throwable.getMessage()   , Toast.LENGTH_SHORT).show();
                                }

                        ));

            }else{
                Toast.makeText(getApplicationContext(), "Mật khẩu và xác nhận mật khẩu phải khớp nhau!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}