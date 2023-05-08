package com.example.physio_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ImageButton add_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        searchView = findViewById(R.id.searchView);
        add_Button = findViewById(R.id.add_Button);
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
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
    }
}