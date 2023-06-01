package com.example.physio_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyAdapter.UserClickListener {

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
    private CardView cardView;

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<User> userList;
    //List<User> filteredList;


    private final String url = "http://192.168.56.1/logHistory.php";

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
        add_Button = findViewById(R.id.add_Button);

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
        imageButton1 = findViewById(R.id.imageButton4);
        imageButton2 = findViewById(R.id.imageButton5);


        userList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(userList,MainActivity.this,this::clicked_user);
        recyclerView.setAdapter(adapter);


        //loadUsers();

        setUsersInfo();
        prepareRecyclerView();

    }
    //Arxiko gemisma listas me users
    public void setUsersInfo(){
        userList.add(new User("John", "Kontaxis", " 0100000000"));
        userList.add(new User("Elisavet", "Kanidou", " 0200000000"));
        userList.add(new User("Nikos", "Sakellaris", " 0300000000"));
        userList.add(new User("Kostas", "Thomson", " 0400000000"));
        userList.add(new User("Eleftheria", "Taggili", " 0500000000"));
        userList.add(new User("Christos", "Giamakidis", " 0600000000"));
        userList.add(new User("Stelia", "Spryridopoulou", " 0700000000"));
        userList.add(new User("Stathis", "Iosifidis", " 0800000000"));
        userList.add(new User("Vasilis", "Tsavalias", " 0900000000"));
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
        startActivity(new Intent(this,Selected_User.class).putExtra("data",user));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.searchView){
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




