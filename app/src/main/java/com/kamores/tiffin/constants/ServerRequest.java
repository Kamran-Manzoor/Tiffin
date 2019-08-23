package com.kamores.tiffin.constants;

import com.kamores.tiffin.models.Supplier;
import com.kamores.tiffin.models.Item;
import com.kamores.tiffin.models.Supplier_Model;
import com.kamores.tiffin.models.User;
import com.kamores.tiffin.models.UserModel;

public class ServerRequest {
    private String operation;
    private String email;
    private User user;
    Supplier supplier;
    Supplier_Model supplier_model;
    Item items;
    UserModel userModel;

    public void setSupplier_model(Supplier_Model supplier_model) {
        this.supplier_model = supplier_model;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setItems(Item items) {
        this.items = items;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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