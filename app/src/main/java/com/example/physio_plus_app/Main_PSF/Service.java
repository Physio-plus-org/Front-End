package com.example.physio_plus_app.Main_PSF;

public class Service {
        private String title;
        private String code;
        private String description;
        private String cost;

        public Service(String title, String code, String description, String cost) {
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
        public String getCost() {return cost;}
}

