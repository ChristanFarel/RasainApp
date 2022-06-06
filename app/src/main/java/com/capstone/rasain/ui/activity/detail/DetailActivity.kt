package com.capstone.rasain.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.databinding.ActivityDetailBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.response.Results

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var key: String
    private var favorite: FavoriteFoodEntity? = null
    private var isFavorite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailViewModel::class.java]

        key = intent.getStringExtra(ListRecipeAdapter.key).toString()

        favorite = FavoriteFoodEntity()

        detailViewModel.getDetailRecipe(key).observe(this) {
            setFavorite(it)
            Glide.with(this)
                .load(it.thumb)
                .into(binding.imgRecipeDetail)
            binding.txtNameRecipeDetail.text = it.title
            binding.txtDiffRecipeDetail.text = it.dificulty
            binding.txtPortionRecipeDetail.text = it.servings
            binding.txtTimeRecipeDetail.text = it.times

            var ingd: String = ""
            var step: String = ""
            for (x in it.step!!) {
                step += x + "\n"
            }

            for (x in it.ingredient!!) {
                ingd += x + "\n"
            }

            binding.txtIngRecipeDetail.text = ingd
            binding.txtHowToCookRecipeDetail.text = step

//            favorite = FavoriteFoodEntity(id = 0,key = key, title = it.title.toString(), imgUrl = it.thumb.toString())
        }

        detailViewModel.getKey(key).observe(this) { checked ->
            isFavorite = checked
            if (checked) {
                binding.imgBtnFavDetail.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_favoriteddetail))
//                    detailViewModel.delFav(favorite as FavoriteFoodEntity)
//                    favorite = FavoriteFoodEntity()
            } else {
                binding.imgBtnFavDetail.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_lovedetail))
//                    favorite.let { detailViewModel.insertFavorite(favorite as FavoriteFoodEntity) }
            }
        }
    }

    private fun setFavorite(favFood: Results) {
        binding.imgBtnFavDetail.setOnClickListener {
            with(favorite) {
                this?.id = 0
                this?.key = key
                this?.title = favFood.title.toString()
                this?.imgUrl = favFood.thumb.toString()
            }

            if (isFavorite == true) {
                detailViewModel.delFav(key)
            } else {
                detailViewModel.insertFavorite(favorite as FavoriteFoodEntity)
            }
        }
    }
}