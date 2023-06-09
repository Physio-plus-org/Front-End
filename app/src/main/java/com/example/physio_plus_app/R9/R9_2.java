package com.example.physio_plus_app.R9;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class R9_2 extends AppCompatActivity implements SelectListenerR9 {
    private  final String myIP = "https://physioplus.000webhostapp.com/R9/";
    private RequestListR9 requestList;

    ArrayList<HourModelR9> hourModel = new ArrayList<>();



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9_2_activity);

        Locale locale = new Locale("EL", "GR");
        Locale.setDefault(locale);

        //start of actionBar
//        ActionBar actionBar;
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.r9_custom_image,null);
//        actionBar.setCustomView(view);
//
//        // Define ColorDrawable object and parse color
//        // using parseColor method
//        // with color hash code as its parameter
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("white"));
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
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
//                switch (item.getItemId()){
//                    case R.id.calendar:
//                        openActivity1();
//                        break;
//                    case R.id.money:
//                        break;
//                }
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

        requestList = new RequestListR9(myIP, range_start, range_end);


        RecyclerView hourRecyclerView = findViewById(R.id.hourRecyclerView);
        List<String> hours = new ArrayList<>();
        for(int i=0; i<12; i++) {
            hours.add(i, i+9+":00");
        }

        hourRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hourRecyclerView.setAdapter(new HourViewAdapterR9(getApplicationContext(),hours,this, bundle));

    }

    private void openActivity1() {
        Intent intent = new Intent(this, R9.class);
        startActivity(intent);
    }



    @Override
    public void onItemCklicked(String hourModel, Bundle bundle) {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(R9_2.this);



        builder.setIcon(R.drawable.r9_baselinewarning24);




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

                RequestObjectR9 myReq = new RequestObjectR9("999999999", "404116263213", req, "UPCOMING");
                String test = myReq.toJSON();
                System.out.println(test);

                OkHttpHandlerR9 myHandler = new OkHttpHandlerR9();
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
                        (R.drawable.r9_round_warning);
            }
        });
        alertDialog.show();


    }
}