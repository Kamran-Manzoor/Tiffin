package com.kamores.tiffin.models;


public class Supplier_Model {
    String sup_id;
    String day;
    String name;
    String address;
    String contact;
    String supplier_image;

    public void setSupplier_image(String supplier_image) {
        this.supplier_image = supplier_image;
    }

    public String getSupplier_image() {
        return supplier_image;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
}
