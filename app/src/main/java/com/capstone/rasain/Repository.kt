package com.capstone.rasain

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.database.local.room.FavoriteDao
import com.capstone.rasain.response.*
import com.capstone.rasain.retrofit.ApiServiceMasakApa
import com.capstone.rasain.retrofit.ApiServiceRasainApp
import com.capstone.rasain.util.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.capstone.rasain.Result
import com.capstone.rasain.retrofit.ApiConfig
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class Repository(private val apiServiceMasakApa: ApiServiceMasakApa, private  val favFood: FavoriteDao, private val appExecutors: AppExecutors, private val apiServiceRasainApp: ApiServiceRasainApp, private val preference: Preference ) {

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

    fun getDetailRecipe(key: String): LiveData<Results>{
        val allRecipe = MutableLiveData<Results>()

        val client = apiServiceMasakApa.getDetailRecipe(key)
        client.enqueue(object: Callback<DetailRecipeResponse> {
            override fun onResponse(
                call: Call<DetailRecipeResponse>,
                response: Response<DetailRecipeResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allRecipe.value = responseBody.results!!
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<DetailRecipeResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return allRecipe
    }

    fun getCategory(): LiveData<ArrayList<ResultsCategory>>{
        val allCategory = MutableLiveData<ArrayList<ResultsCategory>>()

        val client = apiServiceMasakApa.getCategory()
        client.enqueue(object: Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allCategory.value = responseBody.results!!
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return allCategory
    }

    fun getFavTitle(title: String): LiveData<Boolean> = favFood.checkTitle(title)

    fun getFavKey(key: String): LiveData<Boolean> = favFood.checkKey(key)

    fun insertFavorite(fav: FavoriteFoodEntity) {
        appExecutors.diskIO.execute {
            favFood.insertFav(fav)
        }
    }

    fun delFav(key: String){
        appExecutors.diskIO.execute{
            favFood.deleteFav(key)
        }
    }

    fun getAllFav(): LiveData<List<FavoriteFoodEntity>> = favFood.getFav()

    fun register(fullName: String, email: String, pass: String): LiveData<Result<Boolean>>{
        val register = MutableLiveData<Result<Boolean>>()
        register.value = Result.Loading
        val client = apiServiceRasainApp.register(fullName, email, pass)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("rb",responseBody.toString())
                        register.value = Result.Success(true)
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }else{
                    register.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                register.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return register
    }

    fun login(email: String, pass: String): LiveData<Result<Boolean>>{
        val login = MutableLiveData<Result<Boolean>>()
        login.value = Result.Loading
        val client = apiServiceRasainApp.login(email, pass)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        login.value = Result.Success(true)
                        MainScope().launch {
                            preference.saveTokenUser(responseBody.data.user)
                        }
                    } else {
                        Log.e(ContentValues.TAG, "onFailure1: ${response.message()}")
                    }
                }else{
                    login.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                login.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure2: ${t.message}")
            }
        })
        return login
    }

    fun logout(){
        MainScope().launch {
            preference.logout()
        }
    }

    fun getToken(): LiveData<User>{
        return preference.getTokenUser().asLiveData()
    }

    fun upload(imageMultipart: MultipartBody.Part, token: String): LiveData<ArrayList<PredictionsItem>>{
        val up = MutableLiveData<ArrayList<PredictionsItem>>()
        val service = ApiConfig.getApiServiceRasainApp().upload("Bearer $token",imageMultipart)
        service.enqueue(object : Callback<FoodPredictionResponse> {
            override fun onResponse(
                call: Call<FoodPredictionResponse>,
                response: Response<FoodPredictionResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        up.value = responseBody.data.predictions
                        Log.d("hahah",up.value.toString())
                    }else{
                        Log.e(ContentValues.TAG, "onFailureNya1: ${response.message()}")
                    }
                }
            }
            override fun onFailure(call: Call<FoodPredictionResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailureNya2: ${t.message}")
            }
        })
        return up

    }

    fun searchFood(food: String): LiveData<ArrayList<ResultsItem>>{
        val allFood = MutableLiveData<ArrayList<ResultsItem>>()

        val client = apiServiceMasakApa.searchRecipe(food)
        client.enqueue(object: Callback<NewRecipeResponse> {
            override fun onResponse(
                call: Call<NewRecipeResponse>,
                response: Response<NewRecipeResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allFood.value = responseBody.results
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<NewRecipeResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return allFood
    }
}