package com.capstone.rasain.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class HomeViewModelFragment(private val repository: Repository) : ViewModel() {
    fun getNewRecipe() = repository.getNewRecipe()

    fun getNewRecipeWithLimit(size: Int) = repository.getNewRecipeWithLimit(size)

    fun getCategory() = repository.getCategory()

    fun logout() = repository.logout()

    fun getRecipeByCate(key: String) = repository.getRecipeByCate(key)

    fun getUser() = repository.getToken()
}