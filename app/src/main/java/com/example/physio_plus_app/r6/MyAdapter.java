package com.example.physio_plus_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Event> events;
    Bundle bundle;
    SelectListener listener;

    public MyAdapter(Context context, List<Event> events, SelectListener listener, Bundle bundle) {
        this.context = context;
        this.events = events;
        this.listener = listener;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.event_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
