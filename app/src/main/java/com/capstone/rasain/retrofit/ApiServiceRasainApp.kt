package com.capstone.rasain.retrofit

import com.capstone.rasain.response.FoodPredictionResponse
import com.capstone.rasain.response.LoginResponse
import com.capstone.rasain.response.RegisterResponse
import okhttp3.MultipartBody
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

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun editUser(
        @Header("Authorization") auth: String,
        @Field("fullName") fullName: String?,
        @Field("email") email: String?,
        @Field("password") pass: String?,
        @Path("id") id: String
    ): Call<LoginResponse>
}