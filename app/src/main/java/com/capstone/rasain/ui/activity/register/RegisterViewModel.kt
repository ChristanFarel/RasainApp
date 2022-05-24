package com.capstone.rasain.ui.activity.register

import androidx.lifecycle.ViewModel
import com.capstone.rasain.Repository

class RegisterViewModel(private val repository: Repository): ViewModel() {

    fun register(fullName: String, email: String, pass: String) = repository.register(fullName, email, pass)

}