package com.example.nick_yen.cloudinteractive_yen_chih_hao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThirdActivity extends AppCompatActivity {

    private static String TAG = "watch";

    protected String id;
    protected String title;
    protected String imageUrl;
    //UI
    protected TextView txtId, txtTitle, txtNoData;
    protected ImageView img;
    protected ConstraintLayout btnBack;

    private Bitmap bitmap;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.e(TAG, "handleMessage: " + msg.what + "=: " + msg.obj);
            if (msg.what == 1) {
                bitmap = (Bitmap) msg.obj;
                img.setImageBitmap(bitmap);
            }
        }
    };

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
//            Picasso.get().load(imageUrl).into(img);
            new Thread(new MyThread()).start();

        } else {
            txtNoData.setText("資料連結異常，點選畫面任一處，返回上一頁。");
        }

        btnBack.setOnClickListener(v -> finish());

    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                URL url = new URL(imageUrl);
                URLConnection httpURLConnection = url.openConnection();

                //處理HttpURLConnection獲取InputStream對象时FileNotFoundException的問題
                //似乎HttpURLConnection在連接到具有非標準端口的url時返回404。
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                httpURLConnection.setRequestProperty("Accept", "*/*");
                //end

                InputStream is = httpURLConnection.getInputStream();
                byte[] bytes = getByteInputStream(is);
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                handler.obtainMessage(1, bitmap).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private byte[] getByteInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[512];
        int len;
        while ((len = is.read(buff)) != -1) {
            arrayOutputStream.write(buff, 0, len);
        }
        is.close();
        arrayOutputStream.close();
        return arrayOutputStream.toByteArray();
    }
}