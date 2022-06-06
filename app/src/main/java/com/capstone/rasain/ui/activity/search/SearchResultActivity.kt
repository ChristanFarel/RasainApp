package com.capstone.rasain.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivitySearchResultBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.register.RegisterViewModel
import com.capstone.rasain.ui.activity.search.SearchResultViewModel
import com.capstone.rasain.ui.activity.upload.UploadActivity
import com.capstone.rasain.ui.fragment.home.HomeFragment

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchResultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        val foodFromUpload = intent.getStringExtra(UploadActivity.FOOD)
        val foodFromHome = intent.getStringExtra(HomeFragment.HOMEFOOD)

        searchResultViewModel.searchFood(foodFromUpload.toString()).observe(this,{
            setFoodRecycler(it)
        })

        searchResultViewModel.searchFood(foodFromHome.toString()).observe(this,{
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