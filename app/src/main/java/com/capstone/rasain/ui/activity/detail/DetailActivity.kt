package com.capstone.rasain.ui.activity.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.databinding.ActivityDetailBinding
import com.capstone.rasain.response.Results
import com.facebook.shimmer.ShimmerFrameLayout

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var scroll: ScrollView
    private lateinit var key: String
    private var favorite: FavoriteFoodEntity? = null
    private var isFavorite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        shimmer = binding.shimmerLayout
        shimmer.startShimmer()
        scroll = binding.scrollView

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailViewModel::class.java]

        key = intent.getStringExtra(ListRecipeAdapter.key).toString()

        favorite = FavoriteFoodEntity()

        detailViewModel.getDetailRecipe(key).first.observe(this) {
            when(it){
                is Result.Loading -> {
                    shimmer.visibility = View.VISIBLE
                    scroll.visibility = View.GONE
                }
                is Result.Success -> {
                    shimmer.stopShimmer()
                    shimmer.visibility = View.GONE
                    scroll.visibility = View.VISIBLE
                }
            }
        }

        detailViewModel.getDetailRecipe(key).second.observe(this) {
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
        }

        detailViewModel.getKey(key).observe(this) { checked ->
            isFavorite = checked
            if (checked) {
                binding.imgBtnFavDetail.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_lovedetail_selected))
            } else {
                binding.imgBtnFavDetail.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_lovedetail))
            }
        }

        binding.imgBtnBackDetail.setOnClickListener {
            super.onBackPressed()
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