package com.capstone.rasain.ui.activity.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.databinding.ActivitySplashScreenBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.ui.activity.login.LoginActivity
import com.capstone.rasain.ui.activity.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var splashViewModel: SplashScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()

        splashViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SplashScreenViewModel::class.java]



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

    private fun setupAction() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                splashViewModel.getToken().observe(this) {
                    if (it.token.isEmpty()) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            },
            3000
        )
    }
}