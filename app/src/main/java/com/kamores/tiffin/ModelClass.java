package com.kamores.tiffin;

public class ModelClass {
//    public int image_food;
//    public int image_detail;

    public String sup_name;
    public String service_name;
    public String location;

    public ModelClass(String sup_name, String service_name, String location) {
        this.sup_name = sup_name;
        this.service_name = service_name;
        this.location = location;
    }

    public String getSup_name() {
        return sup_name;
    }

    public String getService_name() {
        return service_name;
    }

    public String getLocation() {
        return location;
    }
}