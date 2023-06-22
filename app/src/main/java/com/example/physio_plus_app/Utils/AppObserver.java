package com.example.physio_plus_app.Utils;

import com.example.physio_plus_app.Utils.Entities.User;

public abstract class AppObserver {
    private static User user;
    public static String getId() {
        return user.getId();
    }
    public static void setLoggedUser(User loggedUser) {
        user = loggedUser;
    }

    public static User getLoggedUser() {
        return user;
    }

}
