package com.example.physio_plus_app.Main_PSF;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.physio_plus_app.R;

import java.util.ArrayList;

public class Facilities_details extends AppCompatActivity {

    TextView name, code, desc, price;
    ArrayList<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);

        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        desc = findViewById(R.id.description);
        price = findViewById(R.id.price);

        services = OkHttpHandlerFacilitiesDetails.withdrawData();

        for(Service service: services){
            name.setText(service.getTitle());
            code.setText(service.getCode());
            desc.setText(service.getDescription());
            price.setText(service.getCost());
        }
    }
}