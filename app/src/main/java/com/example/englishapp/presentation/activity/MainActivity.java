package com.example.englishapp.presentation.activity;

import android.content.Context;
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

import com.example.englishapp.R;
import com.example.englishapp.data.remote.ApiApp;
import com.example.englishapp.data.remote.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

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

        });
    }

    private void getChucNang() {

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
        apiApp = RetrofitClient.getInstance().create(ApiApp.class);

        
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