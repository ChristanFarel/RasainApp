package com.capstone.rasain.ui.fragment.article

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class ArticleViewModel(private val repository: Repository) : ViewModel() {

    fun  getListArticle() = repository.getListArticle()

}