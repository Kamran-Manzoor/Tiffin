package com.kamores.tiffin.ModelClasses;

public class ModelClass {


    public String name;
    public String itemName;
    public String address;
    public String supplier_id;
    public String item_image;
    public String price;

    public ModelClass(String name, String itemName, String address, String supplier_id, String item_image, String price) {
        this.name = name;
        this.itemName = itemName;
        this.address = address;
        this.supplier_id = supplier_id;
        this.item_image = item_image;
        this.price = price;
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