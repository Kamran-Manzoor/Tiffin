package com.kamores.tiffin.ModelClasses;

import java.util.ArrayList;

public class User {
    ArrayList<String> service_name;
    ArrayList<String> sup_name;
    ArrayList<String> location;
    ArrayList<String> supplier_id;
    ArrayList<String> sup_contact;
    ArrayList<String> item_image;

    public ArrayList<String> getSup_contact() {
        return sup_contact;
    }

    public ArrayList<String> getSupplier_id() {
        return supplier_id;
    }




    public ArrayList<String> getService_name() {
        return service_name;
    }

    public ArrayList<String> getSup_name() {
        return sup_name;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public ArrayList<String> getItem_image() {
        return item_image;
    }
}