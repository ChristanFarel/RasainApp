package com.capstone.rasain

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.rasain.response.NewRecipeResponse
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.retrofit.ApiServiceMasakApa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiServiceMasakApa: ApiServiceMasakApa) {

    fun getNewRecipe(): LiveData<ArrayList<ResultsItem>>{
        val allRecipe = MutableLiveData<ArrayList<ResultsItem>>()

        val client = apiServiceMasakApa.getNewRecipe()
        client.enqueue(object: Callback<NewRecipeResponse> {
            override fun onResponse(
                call: Call<NewRecipeResponse>,
                response: Response<NewRecipeResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allRecipe.value = responseBody.results
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<NewRecipeResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return allRecipe
    }
}