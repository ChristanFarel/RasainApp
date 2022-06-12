package com.capstone.rasain.ui.activity.detailArticle

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListArticleAdapter
import com.capstone.rasain.databinding.ActivityDetailArticleBinding
import com.facebook.shimmer.ShimmerFrameLayout

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var detailArticleViewModel: DetailArticleViewModel
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var scroll: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        shimmer = binding.shimmerLayout
        shimmer.startShimmer()
        scroll = binding.scrollView

        detailArticleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailArticleViewModel::class.java]

        val key = intent.getStringExtra(ListArticleAdapter.key)

        detailArticleViewModel.getDetailArticle(key.toString()).first.observe(this) {
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
                is Result.Error -> Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
            }
        }

        detailArticleViewModel.getDetailArticle(key.toString()).second.observe(this) {
            Glide.with(this)
                .load(it.thumb)
                .into(binding.imgDetailArticle)

            binding.txtTitleArticle.text = it.title
            binding.txtDescArticle.text = it.description
        }

        binding.imgBtnBackToArticle.setOnClickListener { super.onBackPressed() }
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