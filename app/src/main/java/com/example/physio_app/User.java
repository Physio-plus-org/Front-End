package com.example.physio_app;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String Amka;

    public User(String firstName, String lastName, String Amka) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Amka = Amka;
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


}
