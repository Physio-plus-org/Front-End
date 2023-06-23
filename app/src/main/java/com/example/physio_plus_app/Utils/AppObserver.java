package com.example.physio_plus_app.Utils;

import com.example.physio_plus_app.Utils.Entities.User;

public abstract class AppObserver {
    private static User user;
    public static String getId() {
        return user.getId();
    }
    public static void setLoggedUser(User loggedUser) throws Exception {
        if (loggedUser != null) {
            user = loggedUser;
        } else {
            throw new Exception("Failed to connect user");
        }
    }

    public static User getLoggedUser() {
        return user;
    }

}
