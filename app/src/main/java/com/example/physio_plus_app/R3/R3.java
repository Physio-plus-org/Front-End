package com.example.physio_plus_app.R3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.Pararms.Patient;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class R3 extends AppCompatActivity {

    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;

    EditText name_Patient,surname_Patient,addressPatient,amkaPatient;
    ImageButton createPatient, returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r3_activity);

        name_Patient = findViewById(R.id.username);
        surname_Patient = findViewById(R.id.surname);
        addressPatient = findViewById(R.id.addess);
        amkaPatient = findViewById(R.id.amka);
        createPatient = findViewById(R.id.createbtn);
        returnButton = findViewById(R.id.backbtn);

        /* Topbar */

        ImageView goBackButton = findViewById(R.id.goback);

        /* Layout objects */
        cardContainer = findViewById(R.id.cardContainer);
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);



        /* Network-Database oriented Objects */
        gson = new Gson();
        client = new OkHttpClient();
        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(R3.this);


        goBackButton.setOnClickListener(v -> finish());

        returnButton.setOnClickListener(view -> finish());

    }
    public void onClick(View v) {
        String namePatient = name_Patient.getText().toString();
        String surnamePatient = surname_Patient.getText().toString();
        String addressOfPatient = addressPatient.getText().toString();
        String amPatient = amkaPatient.getText().toString();

        try {
            String response = OkHttpHandler3.loghistory(new Patient(namePatient,surnamePatient, amPatient, addressOfPatient));
            if (!response.isEmpty()){
                Toast.makeText(R3.this, response, Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent();
                JSONObject json = new JSONObject();
                json.put("first_name", namePatient);
                json.put("last_name", surnamePatient);
                json.put("ssrn", amPatient);
                json.put("address", addressOfPatient);
                intent.putExtra("patient", json.toString());
                setResult(2, intent);
                finish();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}