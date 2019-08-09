package com.kamores.tiffin.interfaces;


import com.kamores.tiffin.constants.ServerResponce;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("Tiffin/Test.php")
    //@POST("/TiffinApp/")
    Call<ServerResponce> operation();
    //Call<ServerResponce> operationone(@Body ServerRequest request);
}