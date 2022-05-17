package com.capstone.rasain.ui.activity.home

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class HomeViewModel(private val repository: Repository): ViewModel() {
    fun getNewRecipe() = repository.getNewRecipe()
}