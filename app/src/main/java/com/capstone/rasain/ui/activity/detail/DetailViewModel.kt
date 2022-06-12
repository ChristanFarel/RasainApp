package com.capstone.rasain.ui.activity.detail

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity

class DetailViewModel(private val repository: Repository): ViewModel() {
    fun getDetailRecipe(key: String) = repository.getDetailRecipe(key)

    fun getKey(key: String) = repository.getFavKey(key)

    fun insertFavorite(fav: FavoriteFoodEntity) = repository.insertFavorite(fav)

    fun delFav(key: String) = repository.delFav(key)
}