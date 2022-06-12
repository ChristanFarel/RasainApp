package com.capstone.rasain.ui.activity.register

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
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityRegisterBinding
import com.capstone.rasain.ui.activity.login.LoginActivity
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[RegisterViewModel::class.java]

        setMyButtonEnable()

        binding.edtTxtNameReg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.edtTxtEmailReg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.edtTxtPassReg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.btnRegister.setOnClickListener {
            val fullName = binding.edtTxtNameReg.text.toString()
            val email = binding.edtTxtEmailReg.text.toString()
            val pass = binding.edtTxtPassReg.text.toString()
            registerViewModel.register(fullName, email, pass).observe(this) {

                when (it) {
                    is Result.Success -> {
                        Toast.makeText(this, "Register Success!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        if(!email.isValidEmail()) {
                            Toast.makeText(this, "Register Error! Enter valid email!", Toast.LENGTH_SHORT).show()
                        } else if (!pass.isValidPassword()) {
                            Toast.makeText(this, "Register Error! Password must be greater than 8!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Register Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
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
        val name = binding.edtTxtNameReg.text.toString()
        val email = binding.edtTxtEmailReg.text.toString()
        val password = binding.edtTxtPassReg.text.toString()

        binding.btnRegister.isEnabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }

    private fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun String.isValidPassword() = Pattern.compile("^" + ".{8,}" + "$").matcher(this).matches()
}