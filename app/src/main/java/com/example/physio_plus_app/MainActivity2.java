package com.example.physio_plus_app;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {




    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendar);

//       TextView dateTextView = findViewById(R.id.dayNumberLabel);
//        String selectedDate = getIntent().getStringExtra("selectedDate");
//        dateTextView.setText(selectedDate);

        TextView dayNameText = findViewById(R.id.dayNameLabel);
        String selectedDay = getIntent().getStringExtra("dayName");
        dayNameText.setText(selectedDay);






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


       ListView listView = findViewById(R.id.listView);
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


}