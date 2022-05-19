package com.capstone.rasain.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ActivityDetailBinding
import com.capstone.rasain.di.Injection

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailViewModel::class.java]

        key = intent.getStringExtra(ListRecipeAdapter.key).toString()

        detailViewModel.getDetailRecipe(key).observe(this,{
            Glide.with(this)
                .load(it.thumb)
                .into(binding.imgRecipeDetail)
            binding.txtNameRecipeDetail.text = it.title
            binding.txtDiffRecipeDetail.text = it.dificulty
            binding.txtPortionRecipeDetail.text = it.servings
            binding.txtTimeRecipeDetail.text = it.times
            binding.txtIngRecipeDetail.text = it.ingredient.toString()
            binding.txtHowToCookRecipeDetail.text = it.step.toString()
        })


    }
}