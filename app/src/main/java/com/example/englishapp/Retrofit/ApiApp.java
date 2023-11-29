package com.example.englishapp.Retrofit;

import com.example.englishapp.Model.CacChnangModel;
import com.example.englishapp.Model.DeThiModel;
import com.example.englishapp.Model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiApp {

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("quenmatkhau.php")
    @FormUrlEncoded
    Observable<UserModel> quenmatkhau(
            @Field("email") String email,
            @Field("mobile") String mobile
    );

    @POST("xacthucma.php")
    @FormUrlEncoded
    Observable<UserModel> xacthucma(
            @Field("email") String email,
            @Field("code") String code
    );
    @POST("doimatkhau.php")
    @FormUrlEncoded
    Observable<UserModel> doimatkhau(
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("code") String code,
            @Field("pass") String pass
    );
    @GET("chucnang.php")
    Observable<CacChnangModel> chucnang();

    @POST("laydethi.php")
    @FormUrlEncoded
    Observable<DeThiModel> laydethi(
            @Field("page") int page
    );


}
