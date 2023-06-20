package com.example.physio_plus_app.Main_PSF;

import android.util.Log;

public class Service {
        private String title;
        private String code;
        private String description;
        private double cost;

        public Service(String title, String code, String description, double cost) {
            this.title = title;
            this.code = code;
            this.description = description;
            this.cost = cost;
        }

        public String getTitle() {
            return title;
        }
        public String getCode() {
            return code;
        }
        public String getDescription() {
            return description;
        }
        public double getCost() {return cost;}

    public void print() {
        Log.e("service", title+"|"+code+"|"+cost+"|"+description);
    }
}

