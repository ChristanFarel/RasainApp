package com.capstone.rasain.retrofit

import com.capstone.rasain.response.*
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

    @GET("/api/categorys/recipes/:{key}")
    fun getRecipeByCate(
        @Path("key") key: String
    ): Call<NewRecipeResponse>

    @GET("api/category/article/makanan-gaya-hidup")
    fun getListArticle(): Call<ListArticleResponse>

    @GET("/api/article/makanan-gaya-hidup/:{key}")
    fun getDetailArticle(
        @Path("key") key: String
    ): Call<DetailArticleResponse>

}