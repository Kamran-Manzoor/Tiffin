package com.kamores.tiffin.Constants;

import com.kamores.tiffin.Activities.Suppliers;
import com.kamores.tiffin.ModelClasses.User;

public class ServerResponce {
    private User user;
    private Suppliers suppliers;
    private String message;
    private String result;


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
}
