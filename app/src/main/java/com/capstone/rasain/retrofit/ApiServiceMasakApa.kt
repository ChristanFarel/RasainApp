package com.capstone.rasain.retrofit

import com.capstone.rasain.response.CategoryResponse
import com.capstone.rasain.response.DetailRecipeResponse
import com.capstone.rasain.response.NewRecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMasakApa {

    @GET("/api/recipes")
    fun getNewRecipe(): Call<NewRecipeResponse>

    @GET("/api/search")
    fun searchRecipe(
        @Query("q") food: String
    ): Call<NewRecipeResponse>

    @GET("/api/recipe/:{key}")
    fun getDetailRecipe(
        @Path("key") key: String
    ): Call<DetailRecipeResponse>

    @GET("/api/categorys/recipes")
    fun getCategory(): Call<CategoryResponse>
}