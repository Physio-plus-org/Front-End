package com.example.physio_plus_app.R5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.R3.R3;

import java.util.ArrayList;
import java.util.List;


public class R5 extends AppCompatActivity  {

    /* Topbar */

    private SearchView searchView;
    private ImageButton add_Button;
    private ImageView imageView1;
    private Button imageView2;
    private Button imageView3;
    private ImageView imageView7;
    private ImageView imageView10;
//    private ConstraintLayout constraintLayout;
    private ImageView imageButton1;
    private ImageView imageButton2;
    private CardView cardView;
    private RelativeLayout mainLayout;

    RecyclerView recyclerView;
    public MyAdapter adapter;
    public static List<User> userList;
    //private float mainActivityOpacity = 0.5f;


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
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), R3.class);
                startActivity(intent);
            }
        });
        mainLayout = findViewById(R.id.relative_layout);



//        //Ena layout gia mazi me ta duo koumpia pou pane sto R8,R4 --> Not finished
//        LayoutInflater inflater = getLayoutInflater();
//        View PatientActions = inflater.inflate(R.layout.r5_actions_layout,mainLayout,false);
//
//        mainLayout.addView(PatientActions);



        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new MyAdapter(userList, getApplicationContext());
        recyclerView.setAdapter(adapter);

        try {
            OkHttpHandlerR5.setUsersInfo();
            Log.d("users", userList.toString());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

    }

}




