package com.capstone.rasain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.fragment.home.HomeViewModelFragment
import com.capstone.rasain.ui.home.HomeViewModel

class ViewModelFactory(private val repo: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repo) as T
            }

            modelClass.isAssignableFrom(HomeViewModelFragment::class.java) -> {
                HomeViewModelFragment(repo) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}