package com.kamores.tiffin.ModelClasses;

import java.util.ArrayList;

public class Suppliers {
    String name;
    String address;
    String image_name;
    String image_code;

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

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code;
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
