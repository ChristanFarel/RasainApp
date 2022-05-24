package com.capstone.rasain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity
import com.capstone.rasain.databinding.ItemFavoriteBinding

class ListFavoriteAdapter(private val onFavClick: (FavoriteFoodEntity) -> Unit) :
    ListAdapter<FavoriteFoodEntity, ListFavoriteAdapter.MyViewHolder>(DIFF_CALLBACK) {


    class MyViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(

        binding.root
    ) {
        fun bind(favs: FavoriteFoodEntity) {
            binding.txtTitleFavorite.text = favs.title
            Log.d("gamabr",favs.imgUrl.toString())
            Glide.with(itemView.context)
                .load(favs.imgUrl)
                .into(binding.imgFavorite)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fav = getItem(position)
        holder.bind(fav)

        holder.binding.imgBtnFavorite.setOnClickListener {
            onFavClick(fav)
            Toast.makeText(holder.itemView.context,"terpencet",Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {

        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteFoodEntity> =
            object : DiffUtil.ItemCallback<FavoriteFoodEntity>() {
                override fun areItemsTheSame(
                    oldFav: FavoriteFoodEntity,
                    newFav: FavoriteFoodEntity
                ): Boolean {
                    return oldFav.key == newFav.key
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldFav: FavoriteFoodEntity,
                    newFav: FavoriteFoodEntity
                ): Boolean {
                    return oldFav == newFav
                }
            }
    }


}