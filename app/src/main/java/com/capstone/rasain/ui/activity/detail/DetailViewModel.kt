package com.capstone.rasain.ui.activity.detail

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class DetailViewModel(private val repository: Repository): ViewModel() {
    fun getDetailRecipe(key: String) = repository.getDetailRecipe(key)
}