package com.capstone.rasain.ui.activity.search

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class SearchResultViewModel(private val repo: Repository): ViewModel() {
    fun searchFood(food: String) = repo.searchFood(food)

    fun getNewRecipe() = repo.getNewRecipe()
}