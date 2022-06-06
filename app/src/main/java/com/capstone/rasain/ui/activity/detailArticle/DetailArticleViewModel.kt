package com.capstone.rasain.ui.activity.detailArticle

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class DetailArticleViewModel(private val repository: Repository): ViewModel() {

    fun getDetailArticle(key: String) = repository.getDetailArticle(key)

}