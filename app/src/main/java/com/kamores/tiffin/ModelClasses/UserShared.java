package com.kamores.tiffin.ModelClasses;

import android.content.Context;
import android.content.SharedPreferences;

public class UserShared {

    private String user_id;
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
