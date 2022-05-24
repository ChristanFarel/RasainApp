package com.capstone.rasain.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.databinding.ActivityDetailBinding
import com.capstone.rasain.di.Injection

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var key: String
    private var favorite: FavoriteFoodEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailViewModel::class.java]

        key = intent.getStringExtra(ListRecipeAdapter.key).toString()
        Log.d("key1", key)

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
            favorite = FavoriteFoodEntity(id = 0,key = key, title = it.title.toString(), imgUrl = it.thumb.toString())
        })

        detailViewModel.getKey(key).observe(this,{ checked ->
            binding.imgBtnFavDetail.setOnClickListener {
                if(checked){
                    binding.imgBtnFavDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            this, R.drawable.ic_lovedetail
                        )
                    )
                    detailViewModel.delFav(key)
                    Toast.makeText(this,"terhapus",Toast.LENGTH_SHORT).show()
                }else{
                    binding.imgBtnFavDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            this, R.drawable.ic_favoriteddetail
                        )
                    )
                    favorite?.let { it1 -> detailViewModel.insertFavorite(it1) }
                    Toast.makeText(this,"masukkan",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}