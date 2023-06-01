package com.example.physio_plus_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<HourModel> hourModels;


   public RecyclerViewAdapter(Context context, ArrayList<HourModel> hourModels) {
       this.context=context;
       this.hourModels=hourModels;
   }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_hour_picker,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
       holder.textHour.setText(hourModels.get(position).getHour());
    }

    @Override
    public int getItemCount() {
        return hourModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textHour;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textHour=itemView.findViewById(R.id.hourTextView);
        }
    }
}
