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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity2 extends AppCompatActivity {


    ArrayList<HourModel> hourModel = new ArrayList<>();



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Locale locale = new Locale("el", "GR");
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
       String selectedDay = bundle.getString("dayPicked");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate curDate = LocalDate.now();

            DateTimeFormatter grDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
            String grFormattedDate = curDate.format(grDateFormatter);
            System.out.println("Current Date: " + grFormattedDate);
        }

        dateTextView.setText(selectedDay);






//        RecyclerView recyclerView = findViewById(R.id.hourRecyclerView);
//
//        setUpHourModel();
//
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, hourModel);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        TextView dayNameText = findViewById(R.id.dayNameLabel);
//        String selectedDay = getIntent().getStringExtra("selectedDate");
//        dayNameText.setText(selectedDay);






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


       ListView listView = findViewById(R.id.hourlistView);
        ArrayList<String> dataList = new ArrayList<>();


        dataList.add("9:00");
        dataList.add("10:00");
        dataList.add("11:00");
        dataList.add("12:00");
        dataList.add("13:00");
        dataList.add("14:00");
        dataList.add("15:00");
        dataList.add("16:00");
        dataList.add("17:00");
        dataList.add("18:00");
        dataList.add("19:00");
        dataList.add("20:00");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
               R.layout.rowlayout, dataList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                    finish();

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
        });
    }

    private void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setUpHourModel(){
        String[] hourTime = getResources().getStringArray(R.array.hour_array);

        for (int i = 0; i<hourTime.length; i++){
            hourModel.add(new HourModel(hourTime[i]));
        }
    }



}