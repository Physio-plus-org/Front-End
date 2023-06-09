package com.example.physio_plus_app.R6;

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

public class MyAdapterR6 extends RecyclerView.Adapter<MyViewHolderR6> {


    Context context;
    List<EventR6> events;
    Bundle bundle;
    SelectListenerR6 listener;

    public MyAdapterR6(Context context, List<EventR6> events, SelectListenerR6 listener, Bundle bundle) {
        this.context = context;
        this.events = events;
        this.listener = listener;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public MyViewHolderR6 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderR6(LayoutInflater.from(context).inflate(R.layout.r6_event_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderR6 holder, @SuppressLint("RecyclerView") int position) {
        holder.patientView.setText(events.get(position).getPatient_id());
        int eventIDraw = events.get(position).getEvent_id();
        System.out.println(eventIDraw+"<--");
        String eventID = "Appointment ID: "+ Integer.toString(eventIDraw);
        holder.eventView.setText(eventID);
        holder.dateView.setText(events.get(position).getDateString());
        holder.imageView.setImageResource(events.get(position).getImage());
        holder.cancelEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveItemClicked(events.get(position).getEvent_id());
            }
        });
        holder.confirmEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCompleteItemClicked(events.get(position).getEvent_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
