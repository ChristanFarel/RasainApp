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
import com.capstone.rasain.databinding.ActivitySearchResultBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.fragment.home.HomeFragment

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        searchResultViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[SearchResultViewModel::class.java]

        val foodFromHome = intent.getStringExtra(HomeFragment.HOMEFOOD)

        binding.txtResult.text = resources.getString(R.string.search_result, foodFromHome.toString())

        if (foodFromHome.toString().isNotEmpty()) {
            searchResultViewModel.searchFood(foodFromHome.toString()).second.observe(this) {
                setFoodRecycler(it)

            searchResultViewModel.searchFood(foodFromHome.toString()).first.observe(this){
                when(it){
                    is Result.Loading -> {
                        binding.progBarSearchRes.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progBarSearchRes.visibility = View.GONE
                    }
                    is Result.Error -> {
                        binding.progBarSearchRes.visibility = View.GONE
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            }
        }
    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFood.apply {
            layoutManager = GridLayoutManager(this@SearchResultActivity, 2, GridLayoutManager.VERTICAL, false)
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