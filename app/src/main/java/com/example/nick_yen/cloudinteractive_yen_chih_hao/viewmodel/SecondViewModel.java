package com.example.nick_yen.cloudinteractive_yen_chih_hao.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nick_yen.cloudinteractive_yen_chih_hao.model.Data;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.repository.ApiService;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.repository.ApiWork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondViewModel extends ViewModel {

    private MutableLiveData<List<Data>> dataList;

    public SecondViewModel() {
        dataList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Data>> getMlData() {
        return dataList;
    }

    public void callApi() {

        ApiService apiService = ApiWork.getData().create(ApiService.class);
        Call<List<Data>> call = apiService.getData();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {

                    dataList.postValue(response.body());
                    Log.e("watch", "onResponse: "+response.body());
                } else {
                    dataList.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                dataList.postValue(null);
                Log.d("watch", "onFailure: "+t);
            }
        });
    }
}
