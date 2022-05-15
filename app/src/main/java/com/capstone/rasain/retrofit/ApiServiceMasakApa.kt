package com.capstone.rasain.retrofit

import com.capstone.rasain.response.NewRecipeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceMasakApa {

    @GET("/api/recipes")
    fun getNewRecipe(): Call<NewRecipeResponse>
}