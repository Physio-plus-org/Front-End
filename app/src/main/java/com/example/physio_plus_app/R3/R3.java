package com.example.physio_plus_app.R3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.example.physio_plus_app.Utils.HttpHandler.PhysioCenter.PatientActionsHistoryHandler;
import com.example.physio_plus_app.Utils.RequestParams;
import com.google.gson.Gson;

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
        /* Layout obbjects */
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);


        /* Network-Database oriented Objects */
        gson = new Gson();
        client = new OkHttpClient();




        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });




        goBackButton.setOnClickListener(v -> finish());

        returnButton.setOnClickListener(view -> finish());

    }
    public void onClick(View v) {
        String namePatient = name_Patient.getText().toString();
        String surnamePatient = surname_Patient.getText().toString();
        String addressOfPatient = addressPatient.getText().toString();
        String amPatient = amkaPatient.getText().toString();

        try {
            String response = PatientActionsHistoryHandler.request(
                    new RequestParams()
                            .add("first_name", namePatient)
                            .add("last_name", surnamePatient)
                            .add("ssrn", amPatient)
                            .add("address", addressOfPatient)
            );//OkHttpHandler3.loghistory(new Patient(namePatient,surnamePatient, amPatient, addressOfPatient));
            if (!response.isEmpty()){
                Toast.makeText(R3.this, response, Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}