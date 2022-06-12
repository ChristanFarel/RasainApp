package com.capstone.rasain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rasain.databinding.ItemCategoryHomeBinding
import com.capstone.rasain.response.ResultsCategory
import com.capstone.rasain.ui.fragment.home.HomeFragment

class ListCategoryAdapter(private val listCategory: ArrayList<ResultsCategory>, private val handler: Callbacks) :
    RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {
    private var rowIndex = -1

    class ListViewHolder(var binding: ItemCategoryHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ListViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val allCategory = listCategory[position]
        val txtCate = allCategory.category

        holder.binding.txtCategory.text = txtCate

        holder.itemView.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()

            val fragment: Fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putString(CAT_KEY, allCategory.key)
            fragment.arguments = bundle

            handler.data(allCategory.category.toString(),allCategory.key.toString())
        }

        if (rowIndex == position) {
            val colorBg = holder.itemView.context.resolveColorAttr(android.R.attr.colorPrimary)
            val colorText = holder.itemView.context.resolveColorAttr(com.google.android.material.R.attr.colorPrimaryVariant)
            holder.binding.itemCategory.setBackgroundColor(colorBg)
            holder.binding.txtCategory.setTextColor(colorText)
        } else {
            val colorBg = holder.itemView.context.resolveColorAttr(com.google.android.material.R.attr.colorPrimaryVariant)
            val colorText = holder.itemView.context.resolveColorAttr(com.google.android.material.R.attr.colorOnSecondary)
            holder.binding.itemCategory.setBackgroundColor(colorBg)
            holder.binding.txtCategory.setTextColor(colorText)
        }
    }

    interface Callbacks{
        fun data(catName: String, catKey: String)
    }

    @ColorInt
    fun Context.resolveColorAttr(@AttrRes colorAttr: Int): Int {
        val resolvedAttr = resolveThemeAttr(colorAttr)
        val colorRes = if (resolvedAttr.resourceId != 0) resolvedAttr.resourceId else resolvedAttr.data
        return ContextCompat.getColor(this, colorRes)
    }

    private fun Context.resolveThemeAttr(@AttrRes attrRes: Int): TypedValue {
        val typedValue = TypedValue()
        theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue
    }

    override fun getItemCount(): Int = listCategory.size

    companion object{
        var CAT_KEY = "KEY"
    }
}