package com.capstone.rasain.ui.activity.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityLoginBinding
import com.capstone.rasain.ui.activity.main.MainActivity
import com.capstone.rasain.ui.activity.register.RegisterActivity
import java.util.regex.Pattern

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref")
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

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
                        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    is Result.Error -> {
                        if (!email.isValidEmail()) {
                            Toast.makeText(this, "Login Error! Pastikan email valid!", Toast.LENGTH_SHORT).show()
                        } else if (!pass.isValidPassword()) {
                            Toast.makeText(this, "Login Error! Pastikan password lebih dari 8!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Login Error!", Toast.LENGTH_SHORT).show()
                        }
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

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setMyButtonEnable() {
        val email = binding.edtTxtEmailLogin.text.toString()
        val password = binding.edtTxtPassLogin.text.toString()

        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun String.isValidPassword() = Pattern.compile("^" + ".{8,}" + "$").matcher(this).matches()
}