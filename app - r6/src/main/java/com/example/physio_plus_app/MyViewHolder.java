package com.example.physio_plus_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class MyViewHolder extends RecyclerView.ViewHolder {

    private final String myIP = "192.168.1.100";
    ImageView imageView;
    TextView patientView, eventView, dateView;
    Button confirmEventButton, cancelEventButton;

    Context context;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        patientView = itemView.findViewById(R.id.patient_name);
        eventView = itemView.findViewById(R.id.event_id);
        dateView = itemView.findViewById(R.id.date);
        confirmEventButton = itemView.findViewById(R.id.confirmEventButton);
        cancelEventButton = itemView.findViewById(R.id.cancelEventButton);


        itemView.setOnClickListener((v -> {
            if (!confirmEventButton.isShown() && !cancelEventButton.isShown()){
                confirmEventButton.setVisibility(View.VISIBLE);
                cancelEventButton.setVisibility(View.VISIBLE);
            }
            else {
                confirmEventButton.setVisibility(View.INVISIBLE);
                cancelEventButton.setVisibility(View.INVISIBLE);
            }

        }));



//        confirmEventButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] id = eventView.getText().toString().split("Appointment ID: ");
//                String url = "http://"+myIP+"/physio_stl/requestCompleted.php?id=" + id[1];
//                String response = null;
//                try {
//                    OkHttpHandler okHttpHandler = new OkHttpHandler();
//                    response = okHttpHandler.requestCompleted(url);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(response);
//
//            }
//        });

    }
}
