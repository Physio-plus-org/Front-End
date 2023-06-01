package com.example.physio_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Selected_User extends AppCompatActivity  {

    Intent intent;
    User user;
    TextView infos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_user);
        infos = findViewById(R.id.userinfos);
        intent = getIntent();

        if(intent != null){
            user = (User) intent.getSerializableExtra("data");
            String data = user.getFirstName() +" "+ user.getLastName() +" "+user.getAMKA();
            infos.setText(data);
        }

    }
}
