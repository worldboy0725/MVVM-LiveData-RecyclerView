package com.example.nick_yen.cloudinteractive_yen_chih_hao.repository;

import com.example.nick_yen.cloudinteractive_yen_chih_hao.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/photos")
    Call<List<Data>> getData();
}
