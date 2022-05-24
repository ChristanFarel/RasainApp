package com.capstone.rasain.ui.activity.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivityRegisterBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.ui.activity.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val fullName = binding.edtTxtNameReg.text.toString()
            val email = binding.edtTxtEmailReg.text.toString()
            val pass = binding.edtTxtPassReg.text.toString()
            registerViewModel.register(fullName, email, pass).observe(this,{
                when(it){
                    is Result.Success ->{
                        Toast.makeText(this,"Register Succes!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    is Result.Loading->{
                        Toast.makeText(this,"Register tunggu", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error->{
                        Toast.makeText(this,"Register error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}