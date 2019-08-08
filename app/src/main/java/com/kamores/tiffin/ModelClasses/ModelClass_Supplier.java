package com.kamores.tiffin.ModelClasses;

import java.util.ArrayList;
import java.util.List;

public class ModelClass_Supplier {



   private String itemName;
   private String item_image;
   private String price;
   private String day;
   private String service;

    public ModelClass_Supplier(String itemName, String item_image, String price, String day, String service) {
        this.itemName = itemName;
        this.item_image = item_image;
        this.price = price;
        this.day = day;
        this.service = service;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItem_image() {
        return item_image;
    }

    public String getPrice() {
        return price;
    }

    public String getDay() {
        return day;
    }

    public String getService() {
        return service;
    }
}





