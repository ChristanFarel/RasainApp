package com.capstone.rasain.retrofit

import com.capstone.rasain.response.LoginResponse
import com.capstone.rasain.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceRasainApp {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<RegisterResponse>
}