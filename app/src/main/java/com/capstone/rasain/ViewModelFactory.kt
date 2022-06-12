package com.capstone.rasain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.di.Injection
import com.capstone.rasain.ui.activity.detail.DetailViewModel
import com.capstone.rasain.ui.activity.detailArticle.DetailArticleViewModel
import com.capstone.rasain.ui.activity.login.LoginViewModel
import com.capstone.rasain.ui.activity.register.RegisterViewModel
import com.capstone.rasain.ui.activity.search.SearchResultViewModel
import com.capstone.rasain.ui.activity.splashscreen.SplashScreenViewModel
import com.capstone.rasain.ui.activity.upload.UploadViewModel
import com.capstone.rasain.ui.fragment.article.ArticleViewModel
import com.capstone.rasain.ui.fragment.favorite.FavoriteViewModel
import com.capstone.rasain.ui.fragment.home.HomeViewModel
import com.capstone.rasain.ui.fragment.profile.ProfileViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(SearchResultViewModel::class.java) -> {
                SearchResultViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(DetailArticleViewModel::class.java) -> {
                DetailArticleViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(Injection.provideRepository(context)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}