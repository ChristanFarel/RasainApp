package com.capstone.rasain.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.databinding.ItemRecipeHomeBinding
import com.capstone.rasain.response.NewRecipeResponse
import com.capstone.rasain.response.ResultsItem

class ListRecipeAdapter(private val listRecipe: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<ListRecipeAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRecipeHomeBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRecipeHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allRecipe = listRecipe[position]

        val imgRecipe = allRecipe.thumb
        val nameRecipe = allRecipe.key
        val timeRecipe = allRecipe.times

        Glide.with(holder.itemView.context)
            .load(imgRecipe)
            .into(holder.binding.imgRecipeHome)

        holder.binding.txtRecipeName.text = nameRecipe
        holder.binding.txtTimeRecipe.text = timeRecipe

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Terpencet", Toast.LENGTH_SHORT)
        }

//        val foto = semua.avatarUrl
//        val username = semua.login
//
//        Glide.with(holder.itemView.context)
//            .load(foto)
//            .into(holder.binding.itemGambar)
//
//        holder.binding.itemUsername.text = username
//
//
//        holder.itemView.setOnClickListener {
//
//            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
//            intent.putExtra(ListGHuserAdapter.USERNAME, username)
//            SectionsPagerAdapter.username = username
//            holder.itemView.context.startActivity(intent)
//
//        }
    }

    override fun getItemCount(): Int = listRecipe.size
}