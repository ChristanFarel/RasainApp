package com.capstone.rasain.ui.activity.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivitySearchResultBinding
import com.capstone.rasain.databinding.ActivityUploadResultBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.upload.UploadActivity

class UploadResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadResultBinding
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchResultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        val foodFromUpload = intent.getStringExtra(UploadActivity.FOOD)
        Log.d("food", foodFromUpload.toString())

        searchResultViewModel.searchFood(foodFromUpload.toString()).observe(this) {
            setFoodRecycler(it)
        }

    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFoodUploadResult.apply {
            layoutManager = GridLayoutManager(this@UploadResultActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = ListRecipeAdapter(recipe,10)
        }
    }
}