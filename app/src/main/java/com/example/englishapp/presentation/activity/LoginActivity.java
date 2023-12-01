package com.example.englishapp.presentation.activity;

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
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.englishapp.R;
import com.example.englishapp.data.local.AppDatabase;
import com.example.englishapp.data.local.dao.UserDao;
import com.example.englishapp.data.remote.ApiApp;
import com.example.englishapp.data.remote.RetrofitClient;
import com.example.englishapp.data.remote.UserService;
import com.example.englishapp.data.repository.impl.UserRepositoryImpl;
import com.example.englishapp.databinding.ActivityLoginBinding;
import com.example.englishapp.presentation.ViewModelFactory;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
//Entrypoint for the app
public class LoginActivity extends AppCompatActivity {

    TextView SignUpbtn,btnForgotPass;
    EditText edtGmailSignIn,edtPassword;
    ImageView IconPasshide;
    AppCompatButton btnSignIn;
    private boolean PasswordShowing = false;
    ApiApp apiApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        UserDao userDao = db.userDao();
        UserService userService = RetrofitClient.getInstance().create(UserService.class);
        loginViewModel = new ViewModelProvider(this, new ViewModelFactory(new UserRepositoryImpl(userDao,userService))).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        Log.i("TAG", "onCreate: " + loginViewModel);
        initView();
        initControl();
    }

    private void initControl() {

//        btnForgotPass.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
//            startActivity(intent);
//        });
//        SignUpbtn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,RegisterActivity.class)));

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
                loginViewModel.login(str_email,str_pass);
            }

        });

    }

    private void initView() {
          apiApp = RetrofitClient.getInstance().create(ApiApp.class);
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
        compositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}