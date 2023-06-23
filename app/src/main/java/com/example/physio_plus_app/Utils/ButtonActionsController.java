package com.example.physio_plus_app.Utils;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public interface ButtonActionsController{
    static void BackButtonAction(AppCompatActivity activity) {
        activity.finish();
    }
    static void TransitionButtonAction(AppCompatActivity activity, Class<?> childClass, HashMap<String, String> params) {
        Intent intent = new Intent(activity.getApplicationContext(), childClass);
        if (params != null) {
            for (String key : params.keySet()) {
                intent.putExtra(key, params.get(key));
            }
        }
       activity.startActivity(intent);
    }
}
