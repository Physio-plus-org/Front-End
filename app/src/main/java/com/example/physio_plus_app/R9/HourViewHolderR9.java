package com.example.physio_plus_app.R9;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;

public class HourViewHolderR9 extends RecyclerView.ViewHolder {

    TextView hourView;
    RelativeLayout HourRelativeLayout;

    public HourViewHolderR9(@NonNull View itemView) {
        super(itemView);
        hourView = itemView.findViewById(R.id.hourTextView);
        HourRelativeLayout = itemView.findViewById(R.id.HourRelativeLayout);
    }
}
