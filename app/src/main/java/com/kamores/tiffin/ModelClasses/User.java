package com.kamores.tiffin.ModelClasses;

import java.util.ArrayList;

public class User {
    ArrayList<String> itemName;
    ArrayList<String> name;
    ArrayList<String> address;
    ArrayList<String> price;
    ArrayList<String> item_image;
    ArrayList<String> supplier_id;

    String contact;
    String email;
    String password;

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getSupplier_id() {
        return supplier_id;
    }

    public ArrayList<String> getItemName() {
        return itemName;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public ArrayList<String> getItem_image() {
        return item_image;
    }
}