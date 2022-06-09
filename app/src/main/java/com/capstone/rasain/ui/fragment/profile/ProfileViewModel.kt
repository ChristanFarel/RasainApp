package com.capstone.rasain.ui.fragment.profile

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    fun getUser() = repository.getToken()

    fun editUser(userId: String, token: String, pass: String?, name: String?, email: String? ) = repository.updateUser(userId,token,name,pass,email)
}