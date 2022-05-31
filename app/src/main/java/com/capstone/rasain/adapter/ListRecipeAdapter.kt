package com.capstone.rasain.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.databinding.ItemRecipeHomeBinding
import com.capstone.rasain.response.NewRecipeResponse
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.detail.DetailActivity

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
        val timeRecipe = allRecipe.times
        var title: String? = ""

        for (x in allRecipe.key){
            if (x.equals('-')){
                title += " "
            }else{
                title += x
            }
        }


        Glide.with(holder.itemView.context)
            .load(imgRecipe)
            .into(holder.binding.imgRecipeHome)

        holder.binding.txtRecipeName.text = title
        holder.binding.txtTimeRecipe.text = timeRecipe

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(key, allRecipe.key)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listRecipe.size

    companion object{
        var key = "KEY"
    }
}