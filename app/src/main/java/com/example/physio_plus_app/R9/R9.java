package com.example.physio_plus_app.R9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R10.R10;
import com.example.physio_plus_app.Utils.ButtonActionsController;

import java.util.HashMap;


//import com.cheesecake.mytoxictraits.Week.ThisLocalizedWeek;

public class R9 extends AppCompatActivity{
    private String patient_id = "12345678";
//    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r9_activity);

        ImageView financialMovesButton = findViewById(R.id.addPatientFootbar);

//        button= findViewById(R.id.buttonNext);
//        Bundle args = new Bundle();
//        //start of actionBar
//        ActionBar actionBar;
//        actionBar = getSupportActionBar();
//
//        assert actionBar != null;
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
//
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
//        //end of actionBar
//        Locale locale = new Locale("EL");
//        Locale.setDefault(locale);


        String url = "https://physioplus.000webhostapp.com/R7/TestPrint.php?range_start=2023-04-01&range_end=2023-07-01";
        try {
            OkHttpHandlerR9 okHttpHandler = new OkHttpHandlerR9();
            okHttpHandler.testPrint(url);
            System.out.println("Main Activity testPrint");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Topbar */
        ImageView goBackButton = findViewById(R.id.goback);
        goBackButton.setOnClickListener(v -> finish());
    }

    private void openActivity2() {
        Intent intent = new Intent(this, R9_2.class);
        startActivity(intent);
    }

    protected HashMap<String,String> getR10Params() {
        HashMap<String, String> params = new HashMap<>();
        params.put("patient_id", this.patient_id);
        return params;
    }

    public void GoToR10(View view) {
        ButtonActionsController.TransitionButtonAction(this, R10.class, getR10Params());
    }




}