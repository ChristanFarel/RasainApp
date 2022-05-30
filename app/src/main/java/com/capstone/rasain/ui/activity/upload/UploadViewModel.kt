package com.capstone.rasain.ui.activity.upload

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository
import okhttp3.MultipartBody

class UploadViewModel(private val repo: Repository): ViewModel() {

    fun getToken() = repo.getToken()

    fun upload(imageMultipart: MultipartBody.Part, token: String) = repo.upload(imageMultipart, token)
}