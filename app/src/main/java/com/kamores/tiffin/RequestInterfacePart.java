package com.kamores.tiffin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterfacePart {
    @POST("TiffinApp/")
    Call<ServerResponce> operationone(@Body ServerRequest request);
}
