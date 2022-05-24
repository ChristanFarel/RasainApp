package com.capstone.rasain.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getAllFav() = repository.getAllFav()
    fun delFav(key: String) = repository.delFav(key)
}