package com.example.physio_plus_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private List<String> dataList;

    public CardViewAdapter(List<String> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String data = dataList.get(position);

        try{
            JSONObject jsonObject = new JSONObject(data);
            holder.date_text.setText(jsonObject.getString("date"));
            holder.date_text.setText(jsonObject.getString("hours"));
            holder.date_text.setText(jsonObject.getString("notes"));
        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView date_text, hours_text, notes_text;

        public ViewHolder(View itemView){
            super(itemView);
            date_text = itemView.findViewById(R.id.date_text);
            hours_text = itemView.findViewById(R.id.hours_text);
            notes_text = itemView.findViewById(R.id.notes_text);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
