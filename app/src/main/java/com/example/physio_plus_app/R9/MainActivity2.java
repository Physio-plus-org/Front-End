package com.example.physio_plus_app;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class MainActivity2 extends AppCompatActivity implements SelectListener {
    private  final String myIP = "192.168.2.6";
    private RequestList requestList;

    ArrayList<HourModel> hourModel = new ArrayList<>();



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Locale locale = new Locale("EL", "GR");
        Locale.setDefault(locale);

        //start of actionBar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_image,null);
        actionBar.setCustomView(view);

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("white"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        //end of actionBar


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendar);

        TextView dateTextView = findViewById(R.id.dayNameLabel);
        Bundle bundle = getIntent().getExtras();
        String selectedDay = bundle.getString("dateFull");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate curDate = LocalDate.parse(selectedDay);

            DateTimeFormatter grDateFormatter = DateTimeFormatter.ofPattern("EEEE", locale);
            String grFormattedDate = curDate.format(grDateFormatter);
            dateTextView.setText(grFormattedDate);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.calendar:
                        openActivity1();
                        break;
                    case R.id.money:
                        break;
                }


                return false;
            }
        });


        String dateFull = bundle.getString("dateFull");
        LocalDate localDate = null;
        String range_start = "2023-01-01";
        String range_end = "2023-01-01";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDate = LocalDate.parse(dateFull);
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            range_start = localDate.format(myFormat);
            localDate = localDate.plusDays(1);
            range_end = localDate.format(myFormat);
        }

        requestList = new RequestList(myIP, range_start, range_end);


        RecyclerView hourRecyclerView = findViewById(R.id.hourRecyclerView);
        List<String> hours = new ArrayList<>();
        for(int i=0; i<12; i++) {
            hours.add(i, i+9+":00");
        }

        hourRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hourRecyclerView.setAdapter(new HourViewAdapter(getApplicationContext(),hours,this, bundle));

    }

    private void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onItemCklicked(String hourModel, Bundle bundle) {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);



        builder.setIcon(R.drawable.baselinewarning24);




        // Set Alert Title
        builder.setTitle("Επιβεβαίωση Αιτήματος Ραντεβού");








        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.



        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("Άκυρο", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        builder.setPositiveButton("Ναι", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            //TODO
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String dateFull = bundle.getString("dateFull");
                LocalDate requestedDate = LocalDate.parse(dateFull);

                String[] requestedHour = hourModel.split(":");
                System.out.println(requestedHour[0]);

                LocalDateTime localDateTime = requestedDate.atTime(Integer.parseInt(requestedHour[0]),00);

                DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String req = localDateTime.format(myFormat);


                // Οταν ενωθουν θα εχει τα στοιχεια, για τωρα γινεται random

                RequestObject myReq = new RequestObject("999999999", "404116263213", req, "UPCOMING");
                String test = myReq.toJSON();
                System.out.println(test);

                OkHttpHandler myHandler = new OkHttpHandler();
                try {
                    myHandler.sendRequestDate(test);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                finish();
            }

        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getWindow().setBackgroundDrawableResource
                        (R.drawable.round_warning);
            }
        });
        alertDialog.show();


    }
}