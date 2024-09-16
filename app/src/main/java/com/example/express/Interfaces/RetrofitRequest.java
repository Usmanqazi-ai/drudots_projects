package com.example.express.Interfaces;

import com.example.express.Classes.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitRequest {
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> login(
            @Field("email") String email,
                              @Field("password")String password);


}
