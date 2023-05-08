package com.example.physior4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hide the action bar

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search operation here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter search results here
                return false;
            }
        });

    }

}