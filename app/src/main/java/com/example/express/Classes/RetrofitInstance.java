package com.example.express.Classes;

import com.example.express.Interfaces.RetrofitRequest;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static String API_BASE_URL = "http://medzudo.drudotstech.com/api/";
    private static Retrofit retrofit;
    private static Gson gson;


    public static Retrofit getRetrofitInstance (){
        if (retrofit ==null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();

        }

        return retrofit;
    }

    public static RetrofitRequest getUserService(){

        return getRetrofitInstance().create(RetrofitRequest.class);
    }

}
