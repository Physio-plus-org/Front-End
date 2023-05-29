package com.example.physio_plus_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePatient {

    private String fullnamePatient;
    private String addressPatient;
    private String amkaPatient;

    public CreatePatient(String f, String a, String am){
        this.fullnamePatient = f;
        this.addressPatient = a;
        this.amkaPatient = am;
    }
    public String getAddressPatient() {
        return addressPatient;
    }

    public String getFullnamePatient() {
        return fullnamePatient;
    }

    public String getAmkaPatient() {
        return amkaPatient;
    }
}
