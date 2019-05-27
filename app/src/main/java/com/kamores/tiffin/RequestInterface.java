package com.kamores.tiffin;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/TiffinApp/Test.php")
    Call<ServerResponce> operation();
}