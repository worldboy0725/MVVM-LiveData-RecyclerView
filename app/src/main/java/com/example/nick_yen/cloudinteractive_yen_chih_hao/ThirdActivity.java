package com.example.nick_yen.cloudinteractive_yen_chih_hao;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

public class ThirdActivity extends AppCompatActivity {

    protected String id;
    protected String title;
    protected String imageUrl;
    //UI
    protected TextView txtId, txtTitle, txtNoData;
    protected ImageView img;
    protected ConstraintLayout btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        id = getIntent().getStringExtra("ID");
        title = getIntent().getStringExtra("TITLE");
        imageUrl = getIntent().getStringExtra("IMGURL");
        Log.d("watch", "onCreate: " + id + "/" + txtTitle + "/" + imageUrl);

        initView();
    }

    private void initView() {

        txtId = findViewById(R.id.txt_id);
        txtTitle = findViewById(R.id.txt_title);
        txtNoData = findViewById(R.id.txt_noData);
        img = findViewById(R.id.iv_image);
        btnBack = findViewById(R.id.layout);

        if (id != null && title != null && imageUrl != null) {
            txtId.setText(id);
            txtTitle.setText(title);
            Picasso.get().load(imageUrl).into(img);
        } else {
            txtNoData.setText("資料連結異常，點選畫面任一處，返回上一頁。");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}