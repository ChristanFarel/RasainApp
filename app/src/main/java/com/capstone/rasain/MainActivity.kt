package com.capstone.rasain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.rasain.databinding.ActivityMainBinding
import com.capstone.rasain.ui.home.HomeActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToHome.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}