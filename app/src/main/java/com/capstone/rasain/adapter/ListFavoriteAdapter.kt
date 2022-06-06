package com.capstone.rasain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.databinding.ItemFavoriteBinding
import com.capstone.rasain.helper.FavoriteDiffCallback
import com.capstone.rasain.ui.fragment.favorite.FavoriteFragment
import com.capstone.rasain.ui.fragment.favorite.FavoriteViewModel

class ListFavoriteAdapter : RecyclerView.Adapter<ListFavoriteAdapter.FavoriteHolder>() {
    private val listFav = ArrayList<FavoriteFoodEntity>()
    private var context: Context? = null
    fun setListFav(listFavFood: List<FavoriteFoodEntity>) {
        val diffCallback = FavoriteDiffCallback(listFav,
            listFavFood as ArrayList<FavoriteFoodEntity>
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listFav.clear()
        listFav.addAll(listFavFood)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(listFav[position])
    }

    override fun getItemCount(): Int {
        return listFav.size
    }

    inner class FavoriteHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listFav: FavoriteFoodEntity) {
            context = itemView.context
            with(binding) {
                Glide.with(itemView.context)
                    .load(listFav.imgUrl)
                    .into(imgFavorite)
                txtTitleFavorite.text = listFav.title
                imgBtnFavorite.setOnClickListener {
                    val favViewModel = obtainViewModel(context as AppCompatActivity)
                    favViewModel.delFav(listFav.key.toString())
                }
            }

        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory(context as AppCompatActivity)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }
}