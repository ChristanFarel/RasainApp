package com.capstone.rasain.ui.activity.detailArticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListArticleAdapter
import com.capstone.rasain.databinding.ActivityDetailArticleBinding
import com.capstone.rasain.databinding.ActivityDetailBinding
import com.capstone.rasain.ui.activity.detail.DetailViewModel

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var detailArticleViewModel: DetailArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailArticleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[DetailArticleViewModel::class.java]

        val key = intent.getStringExtra(ListArticleAdapter.key)

        detailArticleViewModel.getDetailArticle(key.toString()).observe(this,{
            Glide.with(this)
                .load(it.thumb)
                .into(binding.imgBtnBackToArticle)

            binding.txtTitleArticle.text = it.title
            binding.txtDescArticle.text = it.description
        })
    }
}