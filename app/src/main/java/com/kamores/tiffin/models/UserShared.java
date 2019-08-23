package com.kamores.tiffin.models;

import android.content.Context;
import android.content.SharedPreferences;

public class UserShared {

    private String user_id;
    private  String supplier_id;
    private String contact;
    Context context;
    SharedPreferences sharedPreferences;

    public UserShared(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }


    public void removerUser(){
        sharedPreferences.edit().clear().apply();
    }

    public String getSupplier_id() {
        supplier_id=sharedPreferences.getString("supplier_id","");
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        sharedPreferences.edit().putString("supplier_id",supplier_id).apply();
        this.supplier_id = supplier_id;
    }

    public String getContact() {
        contact=sharedPreferences.getString("contact","");
        return contact;
    }

    public void setContact(String contact) {
        sharedPreferences.edit().putString("contact",contact).apply();
        this.contact = contact;
    }

    public String getUser_id() {
        user_id=sharedPreferences.getString("user_id","");
        return user_id;
    }

    public void setUser_id(String user_id) {
        sharedPreferences.edit().putString("user_id",user_id).apply();
        this.user_id = user_id;
    }
}
