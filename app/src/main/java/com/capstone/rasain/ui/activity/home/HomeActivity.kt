package com.capstone.rasain.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivityHomeBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.response.ResultsItem

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[HomeViewModel::class.java]


        homeViewModel.getNewRecipe().second.observe(this,{
            setRecycler(it)
        })

    }

    private fun setViewModel(): ViewModel{
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[HomeViewModel::class.java]

        return homeViewModel
    }

    private fun setRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyRecipe.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val listUserAdapter = ListRecipeAdapter(recipe)
        binding.rcyRecipe.adapter = listUserAdapter
    }
}