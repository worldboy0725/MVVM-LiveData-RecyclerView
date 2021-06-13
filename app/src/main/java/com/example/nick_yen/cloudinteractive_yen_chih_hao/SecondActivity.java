package com.example.nick_yen.cloudinteractive_yen_chih_hao;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nick_yen.cloudinteractive_yen_chih_hao.adapter.RecyclerAdapter;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.model.Data;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.viewmodel.SecondViewModel;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private TextView txtNoData;
    private List<Data> dataList;
    private SecondViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setView();
    }

    private void setView() {

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerAdapter = new RecyclerAdapter(this, dataList);
        recyclerView.setAdapter(recyclerAdapter);

        txtNoData = findViewById(R.id.txt_noData);

        viewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        viewModel.getMlData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                if (data != null) {
                    dataList = data;
                    recyclerAdapter.setDataList(data);
                } else {
                    txtNoData.setText("查無資料，請檢查網路連線。");
                }
            }
        });
        viewModel.callApi();
    }
}