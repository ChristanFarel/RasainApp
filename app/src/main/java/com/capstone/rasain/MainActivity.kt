package com.capstone.rasain

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.rasain.response.NewRecipeResponse
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.retrofit.ApiConfig
import com.capstone.rasain.retrofit.ApiServiceMasakApa
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}