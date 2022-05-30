package com.capstone.rasain.retrofit

import com.capstone.rasain.response.FoodPredictionResponse
import com.capstone.rasain.response.LoginResponse
import com.capstone.rasain.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceRasainApp {
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<RegisterResponse>

    @Multipart
    @POST("predictions")
    fun upload(
        @Header("Authorization") auth: String,
        @Part file: MultipartBody.Part
    ): Call<FoodPredictionResponse>
}