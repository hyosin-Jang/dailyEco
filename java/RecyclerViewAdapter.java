package com.example.dailyeco;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<RecyclerData> arrayList;

    public RecyclerViewAdapter(ArrayList<RecyclerData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CustomViewHolder holder, int position) {

        holder.imageIcon.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
        holder.textContent.setText(arrayList.get(position).getTextContent());
        holder.imageNumBack.setBackgroundResource(R.drawable._156_);
        holder.IntNum.setText(arrayList.get(position).getIntNum()+"회");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected AppCompatImageView imageIcon;
        protected TextView textContent;
        protected AppCompatImageView imageNumBack;
        protected TextView IntNum;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageIcon = itemView.findViewById(R.id.icon);
            this.textContent = itemView.findViewById(R.id.content);
            this.imageNumBack = itemView.findViewById(R.id.num_back);
            this.IntNum = itemView.findViewById(R.id.num);

        }
    }
}

