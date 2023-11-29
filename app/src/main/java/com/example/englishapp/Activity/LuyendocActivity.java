package com.example.englishapp.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.Adapter.DeThiAdapter;
import com.example.englishapp.Model.DeThi;
import com.example.englishapp.R;
import com.example.englishapp.Retrofit.ApiApp;
import com.example.englishapp.Retrofit.RetrofitClient;
import com.example.englishapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LuyendocActivity extends AppCompatActivity {

    Toolbar toolbarLuyendoc;
    RecyclerView recyclerViewLuyendoc;
    ApiApp apiApp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    DeThiAdapter adapterLD ;
    List<DeThi> ListDethi;
    LinearLayoutManager linearLayoutManager ;
    Handler handler = new Handler();
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyendoc);
        anhxa();
        ActionToolbar();
        getData(page);
        EventLoad();

    }

    private void EventLoad() {
        recyclerViewLuyendoc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading == false){ // khi mà cái loading == false thì mới quay vòng, khi đến cuối rồi setup về true để k cần load nữa
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == ListDethi.size()-1){
                        isLoading = true;
                        loadMore();

                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ListDethi.add(null);
                adapterLD.notifyItemInserted(ListDethi.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ListDethi.remove(ListDethi.size()-1);
                adapterLD.notifyItemRemoved(ListDethi.size());
                page = page+1;
                getData(page);
                adapterLD.notifyDataSetChanged();
                isLoading = false;
            }
        },2000 );
    }


    private void getData(int page) {
        compositeDisposable.add(apiApp.laydethi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        deThiModel -> {
                            if(deThiModel.isSuccess()){
                                if (adapterLD == null){
                                    ListDethi = deThiModel.getResult();
                                    adapterLD = new DeThiAdapter(getApplicationContext(),ListDethi);
                                    recyclerViewLuyendoc.setAdapter(adapterLD);
                                }else {
                                    int vitri =ListDethi.size()-1;
                                    int soluongadd = deThiModel.getResult().size();
                                    for(int i = 0;i<soluongadd;  i++){
                                        ListDethi.add(deThiModel.getResult().get(i));
                                    }
                                    adapterLD.notifyItemRangeInserted(vitri,soluongadd);
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Hết dữ liệu", Toast.LENGTH_SHORT).show();
                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không có kết nối sever", Toast.LENGTH_LONG).show();
                        }

                ));
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarLuyendoc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void anhxa() {
        toolbarLuyendoc = findViewById(R.id.toolbarLuyenDoc);
        recyclerViewLuyendoc = findViewById(R.id.recyclerviewLuyendoc);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewLuyendoc.setLayoutManager(linearLayoutManager);
        recyclerViewLuyendoc.setHasFixedSize(true);
        ListDethi = new ArrayList<>();
        apiApp = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiApp.class);


    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}