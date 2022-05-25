package com.capstone.rasain.ui.activity.login

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class LoginViewModel(private val repository: Repository): ViewModel() {

    fun login(email: String, pass: String) = repository.login(email, pass)

}