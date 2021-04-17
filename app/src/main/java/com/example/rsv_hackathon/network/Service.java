package com.example.rsv_hackathon.network;

import com.example.rsv_hackathon.network.model.AuthUserRequest;
import com.example.rsv_hackathon.network.model.AuthUserResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {

    @POST("/registration/")
    Observable<AuthUserResponse> signEmail(@Body JsonObject request);

}
