package com.example.physio_plus_app.R7;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;


@SuppressWarnings("ALL")
public class R7 extends AppCompatActivity {


    /* Topbar */


    private static final String TAG = "R7";
    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;
    private final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private static final int MAX_APPOINTMENTS = 3;

    private static final String MOVE_TO_ACCEPTED_URL = "https://physioplus.000webhostapp.com/R7/move_to_accepted.php";
    private static final String TESTCON_URL = "https://physioplus.000webhostapp.com/R7/verify_connection.php";
    private static final String UPCOMING_APPOINTMENTS_URL = "https://physioplus.000webhostapp.com/R7/fetch_upcomingAppoint.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r7_activity);
        StrictMode.setThreadPolicy(policy);


        List<AppointmentR7> appointments = null;
        appointmentManager = new AppointmentForIntentFactory();


        /* Layout objects */
        cardContainer = findViewById(R.id.cardContainer);
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);



        /* Network-Database oriented Objects */
        gson = new Gson();
        client = new OkHttpClient();
        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(R7.this);



        /* Topbar */
        ImageView goBackButton = findViewById(R.id.goback);
        Button calendarTopBar = findViewById(R.id.calendarTopBar);

        /* Footbar */
        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);
        ImageView addPatientFootbarButton = findViewById(R.id.addPatientFootbar);


        goBackButton.setOnClickListener(v -> finish());


        addPatientFootbarButton.setOnClickListener((v -> {
            Intent i = new Intent(R7.this, R3.class);
            startActivity(i);

        }));
        calendarFootbarButton.setOnClickListener(v -> {
            Intent i = new Intent(R7.this, R6.class);
            startActivity(i);
        });


        dropdownAppointmentSharedFactory.testConnection();
        dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(R7.this);

        appointmentManager.fetchUpcomingAppointments(R7.this);
        appointmentManager.populateAppointmentCards(appointments,appointmentCardsContainer,R7.this);
        setCalendarTopBarClickListener(calendarTopBar);

    }

    private void setCalendarTopBarClickListener(View calendarTopBar) {

        calendarTopBar.setOnClickListener(v -> dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(R7.this));

    }
}