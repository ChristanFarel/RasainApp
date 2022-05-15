package com.capstone.rasain.fragment.home

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class HomeViewModelFragment(private val repository: Repository) : ViewModel() {
    fun getNewRecipe() = repository.getNewRecipe()
}