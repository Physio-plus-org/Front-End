package com.example.physio_plus_app;

public class CreatePatient {
    public CreatePatient(String ip) {
        String url= "http://"+ip+"/carsDBServices/populateDropDown.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
