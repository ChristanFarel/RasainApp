package com.capstone.rasain.di

import android.content.Context
import com.capstone.rasain.Repository
import com.capstone.rasain.database.local.room.FavoriteDao
import com.capstone.rasain.database.local.room.FavoriteDatabase
import com.capstone.rasain.retrofit.ApiConfig
import com.capstone.rasain.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FavoriteDatabase.getDatabase(context)
        val favDao = database.favDao()
        val apiService = ApiConfig.getApiService()
        val appExecutors = AppExecutors()
//        val loginPreference = LoginPreference.getInstance(context.dataStore)
        return Repository(apiService, favDao, appExecutors)
    }
}