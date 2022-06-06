package com.capstone.rasain.adapter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.databinding.ItemCategoryHomeBinding
import com.capstone.rasain.response.ResultsCategory
import com.capstone.rasain.ui.activity.detail.DetailActivity
import com.capstone.rasain.ui.fragment.home.HomeFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class ListCategoryAdapter(private val listCategory: ArrayList<ResultsCategory>, val handler: ListCategoryAdapter.Callbacks) :
    RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemCategoryHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoryAdapter.ListViewHolder {
        val binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCategoryAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCategoryAdapter.ListViewHolder, position: Int) {
        val allCategory = listCategory[position]

        val txtCate = allCategory.category

//        val doc = Jsoup.connect(allCategory.url).get()
//        val imageUrl = doc.getElementById("category-header").attr("data-mobile-bg")
//
//        Glide.with(holder.itemView.context)
//            .load(imageUrl)
//            .into(holder.binding.imgCategory)

        holder.binding.txtCategory.text = txtCate


        holder.itemView.setOnClickListener {
            var fragment: Fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putString(CAT_KEY, allCategory.key)
            fragment.arguments = bundle

            handler.data(allCategory.category.toString(),allCategory.key.toString())
        }

    }

    private fun senData(catName: String, catKey: String){
        handler.data(catName, catKey)
    }

    interface Callbacks{
        fun data(catName: String, catKey: String)
    }

    override fun getItemCount(): Int = listCategory.size

    companion object{
        var CAT_KEY = "KEY"
        var CAT_NAME = "NAME"
    }
}