package com.capstone.rasain.ui.activity.search

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivityUploadResultBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.upload.UploadActivity
import java.util.*

class UploadResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadResultBinding
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        searchResultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        val foodFromUpload = intent.getStringExtra(UploadActivity.FOOD)
        val title = foodFromUpload.toString().split("-").joinToString(" ") { it.replaceFirstChar { s ->
            if (s.isLowerCase()) s.titlecase(Locale.getDefault()) else s.toString()
        }}

        binding.txtResult.text = resources.getString(R.string.upload_result, title)

        searchResultViewModel.searchFood(title).second.observe(this) {
            setFoodRecycler(it)
        }

        searchResultViewModel.searchFood(foodFromUpload.toString()).first.observe(this){
            when(it){
                is Result.Loading -> {
                    binding.progBarSearchUpRes.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progBarSearchUpRes.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.progBarSearchUpRes.visibility = View.GONE
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFoodUploadResult.apply {
            layoutManager = GridLayoutManager(this@UploadResultActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = ListRecipeAdapter(recipe,10)
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
}