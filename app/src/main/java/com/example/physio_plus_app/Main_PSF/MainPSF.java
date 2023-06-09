package com.example.physio_plus_app.Main_PSF;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;
import com.example.physio_plus_app.Utils.Entities.Service;
import com.example.physio_plus_app.Utils.HttpHandler.PSF.ServicesHandler;

import java.io.IOException;
import java.util.ArrayList;

public class MainPSF extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Service> services;
    ServiceAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_psf);

        //Topbar
        ImageView goBackButton = findViewById(R.id.goback);

        //Footbar
        ImageButton addButton = findViewById(R.id.add_Button);
        ImageView physioCenter = findViewById(R.id.physiocenter_btn);
        ImageView serviceButton = findViewById(R.id.physio_btn);

//        goBackButton.setOnClickListener(v -> finish());

        addButton.setOnClickListener(v->{
            Intent i = new Intent(MainPSF.this, R2.class );
            startActivity(i);
        });

        physioCenter.setOnClickListener(v->{
            Intent i = new Intent(MainPSF.this, R1.class);
            startActivity(i);
        });

        serviceButton.setOnClickListener(v->{
            Intent i = new Intent(MainPSF.this, R2.class );
            startActivity(i);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        services = new ArrayList<>();
        adapter = new ServiceAdapter(services, getApplicationContext());
        recyclerView.setAdapter(adapter);

        try {
            ServicesHandler.request(services);
//            OkHttpHandlerFacilitiesDetails.withdrawData();
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClick(View view) {
        finish();
    }

}
