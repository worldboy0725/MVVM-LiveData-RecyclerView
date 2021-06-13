package com.example.nick_yen.cloudinteractive_yen_chih_hao.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nick_yen.cloudinteractive_yen_chih_hao.R;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.ThirdActivity;
import com.example.nick_yen.cloudinteractive_yen_chih_hao.model.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Data> dataList;

    public RecyclerAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtTitle;
        ImageView imageView;
        ConstraintLayout btnNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtTitle = itemView.findViewById(R.id.txt_title);
            imageView = itemView.findViewById(R.id.iv_image);
            btnNext = itemView.findViewById(R.id.layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtId.setText(this.dataList.get(position).getId());
        holder.txtTitle.setText(this.dataList.get(position).getTitle());
        Picasso.get().load(this.dataList.get(position).getThumbnailUrl()).into(holder.imageView);
        holder.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThirdActivity.class);
                intent.putExtra("ID", dataList.get(position).getId());
                intent.putExtra("TITLE", dataList.get(position).getTitle());
                intent.putExtra("IMGURL", dataList.get(position).getThumbnailUrl());
                context.startActivity(intent);
                Log.d("watch", "onClick: "+ dataList.get(position).getId()+"/"+dataList.get(position).getTitle()+"/"+dataList.get(position).getThumbnailUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
