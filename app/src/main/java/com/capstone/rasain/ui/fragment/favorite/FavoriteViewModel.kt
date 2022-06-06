package com.capstone.rasain.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getAllFav() = repository.getAllFav()
    fun delFav(key: String) = repository.delFav(key)
}