package com.kamores.tiffin.ModelClasses;

import java.util.ArrayList;

public class Suppliers {
    String name;
    String address;
    String supplier_image;
    String user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupplier_image() {
        return supplier_image;
    }

    public void setSupplier_image(String supplier_image) {
        this.supplier_image = supplier_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    ArrayList<String> item_name;
    ArrayList<String> item_price;


}
