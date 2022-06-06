package com.capstone.rasain.helper

import androidx.recyclerview.widget.DiffUtil
import com.capstone.rasain.database.local.entity.FavoriteFoodEntity

class FavoriteDiffCallback(private val oldFav: ArrayList<FavoriteFoodEntity>, private val newFav: ArrayList<FavoriteFoodEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldFav.size
    }

    override fun getNewListSize(): Int {
        return  newFav.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFav[oldItemPosition].id == newFav[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}