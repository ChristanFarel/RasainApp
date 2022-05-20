package com.capstone.rasain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rasain.databinding.ItemCategoryHomeBinding
import com.capstone.rasain.response.ResultsCategory

class ListCategoryAdapter(private val listCategory: ArrayList<ResultsCategory>) :
    RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemCategoryHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoryAdapter.ListViewHolder {
        val binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCategoryAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCategoryAdapter.ListViewHolder, position: Int) {
        val allCategory = listCategory[position]

        val txtCate = allCategory.category

        holder.binding.txtCategory.text = txtCate


    }

    override fun getItemCount(): Int = listCategory.size
}