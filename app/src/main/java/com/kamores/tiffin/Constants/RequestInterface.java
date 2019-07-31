package com.kamores.tiffin.Constants;


import retrofit2.Call;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("TiffinApp/Test.php")
    //@POST("/TiffinApp/")
    Call<ServerResponce> operation();
    //Call<ServerResponce> operationone(@Body ServerRequest request);
}