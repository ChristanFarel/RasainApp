package com.capstone.rasain.di

import android.content.Context
import com.capstone.rasain.Repository
import com.capstone.rasain.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
//        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
//        val loginPreference = LoginPreference.getInstance(context.dataStore)
        return Repository(apiService)
    }
}