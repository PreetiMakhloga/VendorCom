package com.sterlitepower.vendorcom;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface retrofit_login {
    @Headers("Content-Type: application/json")
    @POST("login/")  //extract last part of API URL
    Call<Object> login(@Body String login_json);
}
