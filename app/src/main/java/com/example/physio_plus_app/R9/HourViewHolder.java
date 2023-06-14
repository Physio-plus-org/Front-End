package com.example.physio_plus_app;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HourViewHolder extends RecyclerView.ViewHolder {

    TextView hourView;
    RelativeLayout HourRelativeLayout;

    public HourViewHolder(@NonNull View itemView) {
        super(itemView);
        hourView = itemView.findViewById(R.id.hourTextView);
        HourRelativeLayout = itemView.findViewById(R.id.HourRelativeLayout);
    }
}
