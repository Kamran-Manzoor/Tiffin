package com.kamores.tiffin.models;

public class ModelClass_Supplier {

   private String itemName;
   private String item_image;
   private String price;


    public ModelClass_Supplier(String itemName, String price, String item_image) {
        this.itemName = itemName;
        this.price = price;
        this.item_image = item_image;
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
}





