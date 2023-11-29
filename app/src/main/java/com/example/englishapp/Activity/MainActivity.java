package com.example.englishapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.englishapp.Adapter.CacChucnangAdapter;
import com.example.englishapp.Model.CacChnang;
import com.example.englishapp.R;
import com.example.englishapp.Retrofit.ApiApp;
import com.example.englishapp.Retrofit.RetrofitClient;
import com.example.englishapp.Utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CacChucnangAdapter cacChucnangAdapter ;
    List<CacChnang> mangChucnang;
    ListView listViewManhinhchinh;
    Toolbar toolbarManhinhchinh;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiApp apiApp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initControl();
        actionBar();
        if (isConnected(this)){
            getChucNang();
            getEventClick();
        }else {
            Toast.makeText(getApplicationContext(), "Không có kết nối internet,vui lòng kiểm tra lại kết nối.", Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
        listViewManhinhchinh.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i){
                case 0:
                    Intent trangchinh = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(trangchinh);
                    break;
                case 1:
                    Intent luyennghe = new Intent(getApplicationContext(),LuyenngheActivity.class);
                    startActivity(luyennghe);
                    break;
                case 2:
                    Intent luyendoc = new Intent(getApplicationContext(),LuyendocActivity.class);
                    startActivity(luyendoc);
                    break;
                case 3:
                    Intent thithu = new Intent(getApplicationContext(),ThithuActivity.class);
                    startActivity(thithu);
                case 4:
                    Intent khoahoc = new Intent(getApplicationContext(),KhoahoconlineActivity.class);
                    startActivity(khoahoc);
                    break;
                case 5:
                    Intent tumoi = new Intent(getApplicationContext(),TumoiActivity.class);
                    startActivity(tumoi);
                case 6:
                    Intent taikhoan = new Intent(getApplicationContext(),TaikhoanActivity.class);
                    startActivity(taikhoan);
                    break;
                case 7:
                    Intent dangxuat = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(dangxuat);
                    Toast.makeText(getApplicationContext(), "Bạn đã đăng xuất.", Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }

    private void getChucNang() {
        compositeDisposable.add(apiApp.chucnang()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cacChnangModel -> {
                            mangChucnang = cacChnangModel.getResult();
                            cacChucnangAdapter = new CacChucnangAdapter(getApplicationContext(),mangChucnang);// khởi tạo adapter
                            listViewManhinhchinh.setAdapter(cacChucnangAdapter);
                        }
                ));

    }

    private void actionBar() {
        setSupportActionBar(toolbarManhinhchinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarManhinhchinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarManhinhchinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void initControl() {

    }

    private void initView() {

        listViewManhinhchinh = findViewById(R.id.listViewManHinhChinh);
        toolbarManhinhchinh = findViewById(R.id.toolbarManhinhchinh);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);

        
        mangChucnang = new ArrayList<>();// khởi tạo mảng
    }
    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}