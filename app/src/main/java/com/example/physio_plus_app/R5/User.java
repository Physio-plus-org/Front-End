package com.example.physio_plus_app.R5;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String Amka;
    private String address;

    public User(String firstName, String lastName, String Amka, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Amka = Amka;
        this.address = address;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getAMKA() {
        return Amka;
    }
    
    public String getAddress() {return address;}


}
