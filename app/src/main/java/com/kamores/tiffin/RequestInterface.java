package com.kamores.tiffin;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/Test.php")
    //@POST("/TiffinApp/")
    Call<ServerResponce> operation();
    //Call<ServerResponce> operationone(@Body ServerRequest request);
}