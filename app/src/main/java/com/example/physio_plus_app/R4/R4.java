package com.example.physio_plus_app.R4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R5.R5;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;


public class R4 extends AppCompatActivity {


    /* Topbar */


    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;


    TextView name_tv;
    TextView age_tv;

    TextView address_tv;
    TextView date_tv;

    TextView ssrn_tv;
    LinearLayout verticalLayout;


    public DisplayInfo handler;
    public Sessions sessions;
    String url1, url2;

    OkHttpClient client2;

    private static final String DISPLAY_INFO = "https://physioplus.000webhostapp.com/R4/displaypatients.php";
    private static final String DISPLAY_SESSIONS= "https://physioplus.000webhostapp.com/R4/displaysessions.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r4_activity);

        Log.d("MainActivity", "onCreate method called");

        name_tv = findViewById(R.id.name_tv);
        ssrn_tv = findViewById(R.id.ssrn_tv);
        address_tv = findViewById(R.id.address_tv);
        date_tv = findViewById(R.id.date_tv);
        verticalLayout = findViewById(R.id.verticalLayout);



        url1 = DISPLAY_INFO;
        url2 = DISPLAY_SESSIONS;



        Log.d("Main Activity", "Sessions is running without problems");





        cardContainer = findViewById(R.id.cardContainer);


        /* Layout obbjects */

        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);


        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });





        gson = new Gson();
        client = new OkHttpClient();






        ImageView goBackView = findViewById(R.id.goback);
        goBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View goBackView) {
                Intent intent = new Intent(R4.this, R5.class);
                startActivity(intent);
            }
        });




        Intent intent = getIntent();
        String message = intent.getStringExtra("patient_id");


        if (message != null) {

            client = new OkHttpClient();
            handler = new DisplayInfo(url1, message, client ,name_tv, ssrn_tv, address_tv, date_tv);

            Log.d("Main Activity", "DisplayInfo is running without problems");

            sessions = new Sessions(url2, message, client, verticalLayout);

            sessions.displaySessions();


        }else{
            Log.d("Main R4","Data parsed from R4 to R5 is null!");
        }


    }






}