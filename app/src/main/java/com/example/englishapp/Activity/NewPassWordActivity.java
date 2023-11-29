package com.example.englishapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
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

public class NewPassWordActivity extends AppCompatActivity {

    EditText edtNewPass,edtConNewPass;
    ImageView IconPassHideUp,IconConPassHideUp;
    AppCompatButton btnChangePass;
    TextView btnSignInchangeNewpass;
    ApiApp apiApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean passwordShowing = false;
    private boolean passwordConShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass_word);
        initView();
        initControl();
    }

    private void initView() {
        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);
        edtNewPass = findViewById(R.id.edtNewPassWord);
        edtConNewPass = findViewById(R.id.edtConNewPassWord);
        IconPassHideUp = findViewById(R.id.IconPassHideUp);
        IconConPassHideUp = findViewById(R.id.IconConPassHideUp);
        btnChangePass = findViewById(R.id.btnChangePassword);
        btnSignInchangeNewpass = findViewById(R.id.btnSignInchangeNewpass);

    }

    private void initControl() {

        IconPassHideUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (passwordConShowing){
                    passwordConShowing = false ;
                    edtNewPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    IconPassHideUp.setImageResource(R.drawable.hide);

                }else {
                    passwordConShowing = true ;
                    edtNewPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IconPassHideUp.setImageResource(R.drawable.eye);
                }
                edtNewPass.setSelection(edtNewPass.length());
            }
        }); // ẩn hiện mật khẩu
        IconConPassHideUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordShowing){
                    passwordShowing = false ;
                    edtConNewPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    IconConPassHideUp.setImageResource(R.drawable.hide);
                }else {
                    passwordShowing = true ;
                    edtConNewPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    IconConPassHideUp.setImageResource(R.drawable.eye);
                }
                edtConNewPass.setSelection(edtConNewPass.length());
            }
        }); // ẩn hiện xác nhận mật khẩu
        btnSignInchangeNewpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });// quay về login nếu k muốn đổi pass nữa
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePass();
            }
        });
    }

    private void updatePass() {
        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getBundleExtra("myPackage");
        String gmail = myBundle.getString("stringGmail");
        String mobile = myBundle.getString("stringMobile");
        String code = myBundle.getString("stringCode");


        String newPass = edtNewPass.getText().toString().trim();
        String connewPass = edtConNewPass.getText().toString().trim();
        if (TextUtils.isEmpty(newPass)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập mật khẩu mới nhé!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(connewPass)) {
            Toast.makeText(getApplicationContext(), "Bạn hãy nhập xác nhận mật khẩu mới nhé!", Toast.LENGTH_SHORT).show();
        }else{
            if(newPass.equals(connewPass)){
                compositeDisposable.add(apiApp.doimatkhau(gmail,mobile,code,newPass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if (userModel.isSuccess()){
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent1);

                                    }else {
                                        Toast.makeText(getApplicationContext(),userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }else {
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