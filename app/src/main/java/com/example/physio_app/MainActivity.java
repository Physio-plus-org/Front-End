package com.example.physio_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


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

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<User> userList;

    private final String url = "http://192.168.1.15/logHistory.php";
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        loadUsers();

        /*Testing recycler view
        userList.add(new User(
                "John",
                "Kont",
                "1"
        ));

        userList.add(new User(
                "Elisavet",
                "Kan",
                "2"
        ));

        adapter = new MyAdapter(MainActivity.this,userList);
        recyclerView.setAdapter(adapter);
        */
    }

    private void loadUsers(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                response -> {
                    try {
                        JSONArray users = new JSONArray(response);

                        for(int i=0; i<users.length(); i++){
                            JSONObject usersObject = users.getJSONObject(i);

                            userList.add(new User(
                                    usersObject.getString("first_name"),
                                    usersObject.getString("last_name"),
                                    usersObject.getString("soc_sec_reg_num")
                            ));

                        }
                        //adapter.notifyDataSetChanged();
                        adapter = new MyAdapter(MainActivity.this,userList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }, error -> Toast.makeText(MainActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
    }
}


