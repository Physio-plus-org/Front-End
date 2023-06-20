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
import com.example.physio_plus_app.R2.R2;
import com.example.physio_plus_app.R5.MyAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class MainPSF extends AppCompatActivity {

    RecyclerView recyclerView;
    static ArrayList<Service> services;
    ServiceAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_psf);

        //Topbar
        ImageView goBackButton = findViewById(R.id.back_button);

        //Footbar
        ImageButton addButton = findViewById(R.id.add_Button);
        ImageView physioCenter = findViewById(R.id.physiocenter_btn);

        goBackButton.setOnClickListener(v -> finish());

        addButton.setOnClickListener(v->{
            Intent i = new Intent(MainPSF.this, R2.class );
            startActivity(i);
        });


        physioCenter.setOnClickListener(v->{
            Intent i = new Intent(MainPSF.this, show_psf.class );
            startActivity(i);

        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        services = new ArrayList<>();
        adapter = new ServiceAdapter(services, getApplicationContext());
        recyclerView.setAdapter(adapter);

        try {
            OkHttpHandlerFacilitiesDetails.withdrawData();
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClick(View view) {
        finish();
    }

}
