package com.example.physio_plus_app.R5;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;


import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R6.R6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class R5 extends AppCompatActivity implements MyAdapter.UserClickListener {



    /* Topbar */


    private SearchView searchView;
    private ImageButton add_Button;
    private ImageView imageView1;
    private Button imageView2;
    private Button imageView3;
    private ImageView imageView7;
    private ImageView imageView10;
    private ConstraintLayout constraintLayout;
    private ImageView imageButton1;
    private ImageView imageButton2;
    private CardView cardView;

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<User> userList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.r5_activity);
//        getSupportActionBar().hide();

        imageView1 = findViewById(R.id.PhysiologoTopbar);
        imageView2 = findViewById(R.id.calendarTopBar);
        imageView3 = findViewById(R.id.ProfilePatientTopbar);
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
        imageButton1 = findViewById(R.id.calendarFootbar);
        imageButton2 = findViewById(R.id.addPatientFootbar);


        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(userList, getApplicationContext(), this::clicked_user);
        recyclerView.setAdapter(adapter);

//        try {
//            OkHttpHandlerR5.setUsersInfo();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


        setUsersInfo();
        prepareRecyclerView();



        /* Topbar */
        ImageView physiologoTopbarButton = findViewById((R.id.PhysiologoTopbar));
        Button profileTopbarButton = findViewById((R.id.ProfilePatientTopbar));
        Button notifTopbarButton = findViewById(R.id.calendarTopBar);
        ImageView goBackButton = findViewById(R.id.goback);

        physiologoTopbarButton.setOnClickListener(v->{
            Intent i = new Intent(R5.this, R6.class );
            startActivity(i);
        });

//        profileTopbarButton.setOnClickListener(v->{
//            Intent i = new Intent(R5.this, Profile.class );
//            startActivity(i);
//        });

        goBackButton.setOnClickListener(v -> finish());


    }

    public void setUsersInfo(){

        OkHttpClient client = new OkHttpClient();

        String url = "http://192.168.56.1/logHistory.php";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Something went wrong");
            }



            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseBody = response.body().string();

                    try {
                        JSONArray jsonArray = new JSONArray(responseBody);

                        if (jsonArray.length() > 0) {

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String firstName = jsonObject.optString("first_name");
                                String lastName = jsonObject.optString("last_name");
                                String Amka = jsonObject.optString("soc_sec_reg_num");

                                User user = new User(firstName, lastName, Amka);
                                userList.add(user);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "Fetching Data failed!");
                    }

                } else {
                    Log.d(TAG, "Error occurred!");
                }
            }
        });




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
        int item_id = item.getItemId();
        if(item_id == R.id.searchView){
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




