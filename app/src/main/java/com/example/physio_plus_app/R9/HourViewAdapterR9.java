package com.example.physio_plus_app.R9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;

import java.util.List;

public class HourViewAdapterR9 extends RecyclerView.Adapter<HourViewHolderR9> {

    Context context;
    List<String> hours;
    SelectListenerR9 listener;
    Bundle bundle;



    public HourViewAdapterR9(Context context, List<String> hours, SelectListenerR9 listener, Bundle bundle) {
        this.context = context;
        this.hours = hours;
        this.listener=listener;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public HourViewHolderR9 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourViewHolderR9(LayoutInflater.from(context).inflate(R.layout.r9_hour_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolderR9 holder, @SuppressLint("RecyclerView") int position) {
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
