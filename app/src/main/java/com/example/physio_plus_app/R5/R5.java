package com.example.physio_plus_app.R5;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;


import com.example.physio_plus_app.R;

import com.example.physio_plus_app.R1.R1;
import com.example.physio_plus_app.R2.R2;
import com.example.physio_plus_app.R3.R3;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R7.R7;
import com.example.physio_plus_app.R_2_5.R_2_5;

import com.example.physio_plus_app.R4.R4;
import com.example.physio_plus_app.R6.R6;
import com.example.physio_plus_app.R8.R8;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class R5 extends AppCompatActivity implements MyAdapter.UserClickListener {

    /* Topbar */

    private SearchView searchView;
    private ImageButton add_Button;
    private ImageView imageView1;
    private Button imageView2;
    private Button imageView3;
    private ImageView imageView7;
    private ImageView imageView10;
    private ConstraintLayout constraintLayout;
    private ImageView imageButton1;
    private ImageView imageButton2;
    private CardView cardView;
    private RelativeLayout mainLayout;

    RecyclerView recyclerView;
    MyAdapter adapter;
    public static List<User> userList;
    private float mainActivityOpacity = 0.5f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.r5_activity);
//        getSupportActionBar().hide();

        imageView1 = findViewById(R.id.PhysiologoTopbar);
        imageView2 = findViewById(R.id.calendarTopBar);
        imageView3 = findViewById(R.id.ProfilePatientTopbar);
        imageView7 = findViewById(R.id.imageView7);
        imageView10 = findViewById(R.id.imageView10);
        add_Button = findViewById(R.id.add_Button);
        mainLayout = findViewById(R.id.relative_layout);

        add_Button.setOnClickListener(v -> {
            add_Button.setAlpha(0.5f);
            searchView.setAlpha(0.5f);
            imageView1.setAlpha(0.5f);
            imageView2.setAlpha(0.5f);
            imageView3.setAlpha(0.5f);
            imageView7.setAlpha(0.5f);
            imageView10.setAlpha(0.5f);
            constraintLayout.setAlpha(0.5f);
            imageButton1.setAlpha(0.5f);
            imageButton2.setAlpha(0.5f);

        });

        constraintLayout = findViewById(R.id.constraintLayout2);
        imageButton1 = findViewById(R.id.calendarFootbar);
        imageButton2 = findViewById(R.id.addPatientFootbar);


        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(userList, getApplicationContext(), this::clicked_user);
        recyclerView.setAdapter(adapter);

        try {
            OkHttpHandlerR5.setUsersInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        prepareRecyclerView();

        /* Topbar */
        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        Button notifTopbarButton = findViewById(R.id.calendarTopBar);
        ImageView goBackButton = findViewById(R.id.goback);

        physiologoTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R5.this, R6.class );
            startActivity(i);
        });

//        profileTopbarButton.setOnClickListener(v->{
//            Intent i = new Intent(R5.this, Profile.class );
//            startActivity(i);
//        });


        /* Footbar */
        ImageView calendarFootbarButton = findViewById(R.id.calendarFootbar);
        ImageView addPatientFootbarButton = findViewById(R.id.addPatientFootbar);
        ImageButton addButton = findViewById(R.id.add_Button);


        addButton.setOnClickListener(v->{
            Intent i = new Intent(R5.this, R3.class );
            startActivity(i);
        });
        goBackButton.setOnClickListener(v -> finish());


        addPatientFootbarButton.setOnClickListener((v->{
            Intent i = new Intent(R5.this, R3.class );
            startActivity(i);

        }));
        calendarFootbarButton.setOnClickListener(v->{
            Intent i = new Intent(R5.this, R6.class);
            startActivity(i);
        });

    }

    public void prepareRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapt_Recycler_view();
    }

    public void Adapt_Recycler_view(){
        adapter = new MyAdapter(userList,this, this::clicked_user);
        recyclerView.setAdapter(adapter);
    }

    public void clicked_user(User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String amka = user.getAMKA();
        String address = user.getAddress();

        LinearLayout newLayout = createLayout(firstName, lastName, amka, address);
        mainLayout.addView(newLayout);

        Intent intent = new Intent(getApplicationContext(), R4.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("amka", amka);
        intent.putExtra("address", address);

        startActivity(intent);

        setComponentsOpacity(mainActivityOpacity);
    }

    private LinearLayout createLayout(final String firstName, final String lastName, final String amka, final String address) {
        LinearLayout newLayout = new LinearLayout(this);
        newLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button buttonHistory = new Button(this);
        buttonHistory.setText("History");
        buttonHistory.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), R4.class);
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("amka", amka);
            intent.putExtra("address", address);
            startActivity(intent);
        });

        Button buttonNewAppointment = new Button(this);
        buttonNewAppointment.setText("Appointment");
        buttonNewAppointment.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), R8.class);
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("amka", amka);
            intent.putExtra("address", address);
            startActivity(intent);
        });

        newLayout.addView(buttonHistory);
        newLayout.addView(buttonNewAppointment);

        return newLayout;
    }

    private void setComponentsOpacity(float opacity) {

        add_Button.setAlpha(opacity);
        searchView.setAlpha(opacity);
        imageView1.setAlpha(opacity);
        imageView2.setAlpha(opacity);
        imageView3.setAlpha(opacity);
        imageView7.setAlpha(opacity);
        imageView10.setAlpha(opacity);
        constraintLayout.setAlpha(opacity);
        imageButton1.setAlpha(opacity);
        imageButton2.setAlpha(opacity);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == R.id.searchView){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

        return super.onCreateOptionsMenu(menu);
    }
}




