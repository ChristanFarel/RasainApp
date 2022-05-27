package com.capstone.rasain.di

import android.content.Context
import com.capstone.rasain.Preference
import com.capstone.rasain.Repository
import com.capstone.rasain.database.local.room.FavoriteDatabase
import com.capstone.rasain.retrofit.ApiConfig
import com.capstone.rasain.ui.activity.login.dataStore
import com.capstone.rasain.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FavoriteDatabase.getDatabase(context)
        val favDao = database.favDao()
        val apiService = ApiConfig.getApiService()
        val apiServiceRasainApp = ApiConfig.getApiServiceRasainApp()
        val appExecutors = AppExecutors()
        val preference = Preference.getInstance(context.dataStore)
        return Repository(apiService, favDao, appExecutors, apiServiceRasainApp, preference)
    }
}