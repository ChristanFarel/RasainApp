package com.capstone.rasain.ui.activity.splashscreen

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class SplashScreenViewModel(private val repo: Repository): ViewModel() {
    fun getToken() = repo.getToken()
}