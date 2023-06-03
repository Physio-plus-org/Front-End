package com.example.physio_plus_app;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class R1 extends AppCompatActivity {
    private Button r1Button;
    private EditText namePhysioEditText;
    private EditText addressPhysioEditText;
    private EditText afmPhysioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r1);

        r1Button = findViewById(R.id.r1_button);
        namePhysioEditText = findViewById(R.id.namePhysio);
        addressPhysioEditText = findViewById(R.id.addressPhysio);
        afmPhysioEditText = findViewById(R.id.afmPhysio);

        r1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePhysio = namePhysioEditText.getText().toString();
                String addressPhysio = addressPhysioEditText.getText().toString();
                String afmPhysio = afmPhysioEditText.getText().toString();

                // Call the psfCreate method with the obtained data
                try {
                    String response = OkHttpHandler.psfCreate(namePhysio, addressPhysio, afmPhysio);

                    if (response.equals("Tax ID number already exists")) {
                        Toast.makeText(R1.this, "Tax ID number already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(R1.this, "Data written to database", Toast.LENGTH_SHORT).show();

                        // Clear the EditText fields
                        namePhysioEditText.getText().clear();
                        addressPhysioEditText.getText().clear();
                        afmPhysioEditText.getText().clear();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(R1.this, "Error writing data to database", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}