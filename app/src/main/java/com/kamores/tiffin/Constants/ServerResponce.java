package com.kamores.tiffin.Constants;

import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.ModelClasses.User;
import com.kamores.tiffin.ModelClasses.UserModel;

public class ServerResponce {
    private User user;
    private Suppliers suppliers;
    private String message;
    private String result;
    UserModel userModel;


    public Suppliers getSuppliers() {
        return suppliers;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public String getResult() {
        return result;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
