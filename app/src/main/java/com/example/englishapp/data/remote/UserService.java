package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
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
    Observable<UserModel> login(
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
}
