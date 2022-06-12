package com.capstone.rasain.ui.activity.search

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivityUploadResultBinding
import com.capstone.rasain.databinding.ActivityViewAllResultBinding
import com.capstone.rasain.response.ResultsItem

class ViewAllResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllResultBinding
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        searchResultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        searchResultViewModel.getNewRecipe().second.observe(this){
            setFoodRecycler(it)
        }

    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFoodViewAll.apply {
            layoutManager = GridLayoutManager(this@ViewAllResultActivity, 2, GridLayoutManager.VERTICAL, false)
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