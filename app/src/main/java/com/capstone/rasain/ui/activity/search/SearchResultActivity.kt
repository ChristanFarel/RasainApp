package com.capstone.rasain.ui.activity.search

import android.app.appsearch.SearchResult
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivitySearchResultBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.upload.UploadActivity
import com.capstone.rasain.ui.activity.upload.UploadViewModel

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var searchViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = intent.getStringExtra(UploadActivity.FOOD)
        Log.d("food", food.toString())

        searchViewModel =  ViewModelProvider(
                this,
        ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        searchViewModel.searchFood(food.toString()).observe(this,{
            setFoodRecycler(it)
        })

    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFood.apply {
            layoutManager = LinearLayoutManager(this@SearchResultActivity, LinearLayoutManager.VERTICAL, false)
//            val listUserAdapter = ListRecipeAdapter(recipe)
//            binding.rcyRecipeFragment.adapter = listUserAdapter
            adapter = ListRecipeAdapter(recipe)
        }
    }
}