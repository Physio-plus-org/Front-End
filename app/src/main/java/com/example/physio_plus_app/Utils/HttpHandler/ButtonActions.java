package com.example.physio_plus_app.Utils.HttpHandler;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.Main_PSF.MainPSF;
import com.example.physio_plus_app.R2.R2;

import java.util.HashMap;

public abstract class ButtonActions extends AppCompatActivity {
    public void BackButtonAction(View view) {
        finish();
    }
    public void TransitionButtonAction(Context context, Object childClass, HashMap<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Intent intent = new Intent(context, childClass.getClass());
        for (String key : params.keySet()) {
            intent.putExtra(key, params.get(key));
        }
        startActivity(intent);
    }
}
