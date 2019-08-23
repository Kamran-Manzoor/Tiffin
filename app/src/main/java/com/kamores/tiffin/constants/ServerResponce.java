package com.kamores.tiffin.constants;

import com.kamores.tiffin.models.Supplier;
import com.kamores.tiffin.models.Supplier_Model;
import com.kamores.tiffin.models.User;
import com.kamores.tiffin.models.UserModel;

public class ServerResponce {
    private User user;
    private Supplier supplier;
    private String message;
    private String result;
    UserModel userModel;
    Supplier_Model supplier_model;

    public Supplier_Model getSupplier_model() {
        return supplier_model;
    }

    public Supplier getSupplier() {
        return supplier;
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
