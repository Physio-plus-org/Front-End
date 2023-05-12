package com.example.physio_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  {

    private SearchView searchView;
    private ImageButton add_Button;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView7;
    private ImageView imageView10;
    private ConstraintLayout constraintLayout;
    private ImageButton imageButton1;
    private ImageButton imageButton2;

    private HttpHandler handler;

    public String firstName;
    public String lastName;

    private final String myIP = "192.168.56.1";
    private UserList u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        u = new UserList(myIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //snew HttpHandler().SearchPatient();
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");

        imageView1 = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView8);
        imageView3 = findViewById(R.id.imageView9);
        imageView7 = findViewById(R.id.imageView7);
        imageView10 = findViewById(R.id.imageView10);
        searchView = findViewById(R.id.searchView);
        add_Button = findViewById(R.id.add_Button);
        constraintLayout = findViewById(R.id.constraintLayout2);
        imageButton1 = findViewById(R.id.imageButton4);
        imageButton2 = findViewById(R.id.imageButton5);
        //searchView.setOnQueryTextListener(this);
    }



    public void myButtonClickHandler(View view) {
        String url= "https://" + myIP + "/logHistory.php";
        try {
            HttpHandler okHttpHandler = new HttpHandler();
            okHttpHandler.phpData(url);
            Toast.makeText(getApplicationContext(), "Selection Logged", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
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

         */
    }
}


