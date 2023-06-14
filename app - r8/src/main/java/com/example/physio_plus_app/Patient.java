package com.example.physio_plus_app;

import androidx.annotation.NonNull;

public class Patient {
    private final String first_name;
    private final String last_name;
    private final String id_number;
    public Patient(String fname, String lname, String pnumber) {
        this.first_name = fname;
        this.last_name = lname;
        this.id_number = pnumber;
    }
    @NonNull
    @Override
    public String toString() {
        return first_name + " " + last_name + " " + id_number;
    }
    public String getFullName() {
        return this.last_name + " " + this.first_name;
    }
    public String getIdNumber() {
        return this.id_number;
    }
}
