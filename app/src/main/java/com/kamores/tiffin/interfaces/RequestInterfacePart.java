package com.kamores.tiffin.interfaces;

import com.kamores.tiffin.constants.ServerRequest;
import com.kamores.tiffin.constants.ServerResponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterfacePart {
    @POST("Tiffin/")
    Call<ServerResponce> operationone(@Body ServerRequest request);
}
