package com.example.nick_yen.cloudinteractive_yen_chih_hao.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWork {

    public static final String API_URL = "https://jsonplaceholder.typicode.com";

    private static Retrofit retrofit;

    public static Retrofit getData(){

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
