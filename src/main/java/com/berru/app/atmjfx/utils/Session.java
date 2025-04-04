package com.berru.app.atmjfx.utils;

import com.berru.app.atmjfx.dto.UserDTO;

public class Session {


    public static UserDTO currentUser;

    public static void setCurrentUser(UserDTO user) {
        currentUser = user;
    }

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}
