package com.example.physio_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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
        searchView.setOnQueryTextListener(this);

    }


    @Override
        public boolean onQueryTextSubmit(String query) {
            // Handle search query submit
            return true;
        }

        @Override
        public boolean onQueryTextChange(String text) {
            // Handle search query text change
            text_filtering(text);
            return true;
        }

    private void text_filtering(String text) {

        String attribute = text;
        Toast.makeText(this, attribute, Toast.LENGTH_SHORT).show();

    }

    public void myButtonClickHandler(View view) {
        // Do something when the button is clicked

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

        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
    }
}