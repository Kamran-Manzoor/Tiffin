package com.kamores.tiffin.Constants;

import com.kamores.tiffin.ModelClasses.Suppliers;
import com.kamores.tiffin.ModelClasses.Items;
import com.kamores.tiffin.ModelClasses.User;
import com.kamores.tiffin.ModelClasses.UserModel;

public class ServerRequest {
    private String operation;
    private String email;
    private User user;
    Suppliers suppliers;
    Items items;
    UserModel userModel;

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}