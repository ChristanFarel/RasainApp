package com.capstone.rasain.ui.activity.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityHomeBinding
import com.capstone.rasain.databinding.ActivityLoginBinding
import com.capstone.rasain.databinding.ActivityRegisterBinding
import com.capstone.rasain.ui.activity.home.HomeActivity
import com.capstone.rasain.ui.activity.home.HomeViewModel
import com.capstone.rasain.ui.activity.main.MainActivity
import com.capstone.rasain.ui.activity.register.RegisterActivity
import com.capstone.rasain.ui.activity.register.RegisterViewModel


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref")
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[LoginViewModel::class.java]

        setMyButtonEnable()

        binding.edtTxtEmailLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.edtTxtPassLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.btnLogin.setOnClickListener {
            val email = binding.edtTxtEmailLogin.text.toString()
            val pass = binding.edtTxtPassLogin.text.toString()

            loginViewModel.login(email, pass).observe(this) {
                when (it) {
                    is Result.Success -> {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    is Result.Error -> {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun setMyButtonEnable() {
        val email = binding.edtTxtEmailLogin.text.toString()
        val password = binding.edtTxtPassLogin.text.toString()

        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }
}