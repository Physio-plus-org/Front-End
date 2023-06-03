package com.example.physio_plus_app;

public class ParamsR3 {

    private String namePatient, surnamePatient, addressPatient, amkaPatient;

    public ParamsR3(String f, String s, String a, String am){
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
