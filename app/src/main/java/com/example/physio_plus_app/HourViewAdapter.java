package com.example.physio_plus_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HourViewAdapter extends RecyclerView.Adapter<HourViewHolder> {

    Context context;
    List<String> hours;
    SelectListener listener;
    Bundle bundle;



    public HourViewAdapter(Context context, List<String> hours,SelectListener listener, Bundle bundle) {
        this.context = context;
        this.hours = hours;
        this.listener=listener;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourViewHolder(LayoutInflater.from(context).inflate(R.layout.hour_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.hourView.setText(hours.get(position));
        holder.HourRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemCklicked(hours.get(position), bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }
}
