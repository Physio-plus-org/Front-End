package com.example.physio_plus_app.R5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.Pararms.Patient;
import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R4.R4;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.AppointmentForIntentFactory;
import com.example.physio_plus_app.R7.DropdownAppointmentSharedFactory;
import com.example.physio_plus_app.R8.R8;
import com.example.physio_plus_app.Utils.HttpHandler.ButtonActionsController;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;


public class R5 extends AppCompatActivity  {

    /* Topbar */

    private RelativeLayout cardContainer;

    AppointmentForIntentFactory appointmentManager;

    TextView redBubble;

    private DropdownAppointmentSharedFactory dropdownAppointmentSharedFactory;
    private Gson gson;
    private OkHttpClient client;

    private SearchView searchView;
    private ImageButton add_Button;
    private ImageView imageView1;
    private Button imageView2;
    private Button imageView3;
    private ImageView imageView7;
    private ImageView imageView10;
//    private ConstraintLayout constraintLayout;
    private ImageView imageButton1;
    private ImageView imageButton2;
    private CardView cardView;
    private ConstraintLayout mainLayout;

    RecyclerView recyclerView;
    public static MyAdapter adapter;
    public static List<Patient> userList;
    //private float mainActivityOpacity = 0.5f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.r5_activity);
//        getSupportActionBar().hide();

        imageView1 = findViewById(R.id.PhysiologoTopbar);
        imageView2 = findViewById(R.id.calendarTopBar);
        imageView3 = findViewById(R.id.ProfilePatientTopbar);
        imageView7 = findViewById(R.id.imageView2);
        imageView10 = findViewById(R.id.imageView10);
        add_Button = findViewById(R.id.add_Button);
        add_Button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), R3.class);
            startActivityForResult(intent, 2);
        });
        mainLayout = findViewById(R.id.relative_layout);


        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new MyAdapter(userList, getApplicationContext());
        recyclerView.setAdapter(adapter);

        /* Layout obbjects */
        cardContainer = findViewById(R.id.cardContainer);
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);
        LinearLayout appointmentCardsContainer = findViewById(R.id.appointmentCardsContainer);



        /* Network-Database oriented Objects */
        gson = new Gson();
        client = new OkHttpClient();
        dropdownAppointmentSharedFactory = new DropdownAppointmentSharedFactory(R5.this);


        try {
            OkHttpHandlerR5.setUsersInfo();
            Log.d("users", userList.toString());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String search_user = newText;
                adapter.getFilter().filter(newText);
                return false;
            }
    });


        /* Topbar */
        ImageView goBackButton = findViewById(R.id.goback);

        /* Footbar */
        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);


        ImageView addPatientFootbarButton = findViewById(R.id.addPatientFootbar);


        Button appointmentsButton = findViewById(R.id.calendarTopBar);

        appointmentsButton.setOnClickListener(v->{
            dropdownAppointmentSharedFactory.fetchUpcomingAppointmentsForDropdown(this,redBubble);
        });

        /* Layout obbjects */
        redBubble = findViewById(R.id.redBubbleText);
        redBubble.setVisibility(View.GONE);


        goBackButton.setOnClickListener(v -> finish());


//        addPatientFootbarButton.setOnClickListener((v->{
//            Intent i = new Intent(R5.this, R3.class );
//            startActivity(i);
//
//        }));
//        calendarFootbarButton.setOnClickListener(v->{
//            Intent i = new Intent(R5.this, R6.class);
//            startActivity(i);
//        });
        calendarFootbarButton.setOnClickListener(v -> finish());

    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {

            //convert from JSON string to JSONObject
            try {
                String jsonText = data.getStringExtra("patient");
                JSONObject newJObject = new JSONObject(jsonText);
                Patient user = new Patient(newJObject.get("first_name").toString(), newJObject.get("last_name").toString(), newJObject.get("ssrn").toString(), newJObject.get("address").toString());
                userList.add(user);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


//            recreate();
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

    protected HashMap<String, String> getPatientIdParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("patient_id", adapter.getPatientId());
        return params;
    }
    public void GoToPatientHistory(View view) {
        ButtonActionsController.TransitionButtonAction(this, R4.class, getPatientIdParams());
    }

    public void GoToPatientActions(View view) {
        ButtonActionsController.TransitionButtonAction(this, R8.class, getPatientIdParams());
    }

}




