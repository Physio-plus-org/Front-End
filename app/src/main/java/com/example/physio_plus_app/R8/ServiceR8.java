package com.example.physio_plus_app.R8;

import androidx.annotation.NonNull;

import java.util.Locale;

public class ServiceR8 {
    private final String title;
    private final String description;
    private final String code;
    private final double cost;
    public ServiceR8(String title, String description, String code, double cost) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.cost = cost;
    }
    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%s %.1f €", this.title, this.cost);
    }

    public String getCode() {
        return this.code;
    }

    public double getCost() {
        return this.cost;
    }

    public String getTitle() {
        return this.title;
    }
}
