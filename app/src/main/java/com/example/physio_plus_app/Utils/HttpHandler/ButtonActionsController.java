package com.example.physio_plus_app.Utils.HttpHandler;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public abstract class ButtonActionsController extends AppCompatActivity {
    public static void BackButtonAction(AppCompatActivity activity) {
        activity.finish();
    }
    public static void TransitionButtonAction(AppCompatActivity activity, Class<?> childClass, HashMap<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Intent intent = new Intent(activity.getApplicationContext(), childClass);
        for (String key : params.keySet()) {
            intent.putExtra(key, params.get(key));
        }
       activity.startActivity(intent);
    }
}
