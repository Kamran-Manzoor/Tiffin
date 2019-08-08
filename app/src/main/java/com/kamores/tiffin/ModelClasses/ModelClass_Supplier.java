package com.kamores.tiffin.ModelClasses;

public class ModelClass_Supplier {


    public String name;
    public String itemName;
    public String address;
    public String supplier_id;
    public String item_image;
    public String price;
    public String contact;
    public String day;
    public String type;
    public String servingTime;

    public ModelClass_Supplier(String name, String itemName, String address, String supplier_id, String item_image, String price, String contact, String day, String type, String servingTime) {
        this.name = name;
        this.itemName = itemName;
        this.address = address;
        this.supplier_id = supplier_id;
        this.item_image = item_image;
        this.price = price;
        this.contact = contact;
        this.day = day;
        this.type = type;
        this.servingTime = servingTime;
    }

    public String getContact() {
        return contact;
    }

    public String getDay() {
        return day;
    }

    public String getType() {
        return type;
    }

    public String getServingTime() {
        return servingTime;
    }

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }

    public String getAddress() {
        return address;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public String getItem_image() {
        return item_image;
    }

    public String getPrice() {
        return price;
    }
}