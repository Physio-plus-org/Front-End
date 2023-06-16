package com.example.physio_plus_app.R5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;

public class Selected_User extends AppCompatActivity  {

    Intent intent;
    User user;
    TextView infos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r5_selected_user);
        infos = findViewById(R.id.userinfos);
        intent = getIntent();

        if(intent != null){
            user = (User) intent.getSerializableExtra("data");
            String data = user.getFirstName() +" "+ user.getLastName() +" "+user.getAMKA();
            infos.setText(data);
        }

    }
}
