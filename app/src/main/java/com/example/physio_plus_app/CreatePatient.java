package com.example.physio_plus_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePatient {

    private String namePatient;
    private String surnamePatient;
    private String addressPatient;
    private String amkaPatient;

    public CreatePatient(String f, String s, String a, String am){
        this.namePatient = f;
        this.surnamePatient = s;
        this.addressPatient = a;
        this.amkaPatient = am;
    }
    public String getAddressPatient() {
        return addressPatient;
    }

    public String getnamePatient() {
        return namePatient;
    }

    public String getSurnamePatient(){return surnamePatient;}

    public String getAmkaPatient() {
        return amkaPatient;
    }
}
