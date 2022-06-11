package com.capstone.rasain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rasain.R
import com.capstone.rasain.databinding.ItemCategoryHomeBinding
import com.capstone.rasain.response.ResultsCategory
import com.capstone.rasain.ui.fragment.home.HomeFragment
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlin.concurrent.thread
import kotlin.random.Random

class ListCategoryAdapter(private val listCategory: ArrayList<ResultsCategory>, val handler: ListCategoryAdapter.Callbacks) :
    RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    private var row_index = -1
    private var firstRandom = true

    class ListViewHolder(var binding: ItemCategoryHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCategoryAdapter.ListViewHolder {
        val binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCategoryAdapter.ListViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ListCategoryAdapter.ListViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val allCategory = listCategory[position]

//        if (firstRandom) {
//            val randomCat = Random.nextInt(0, listCategory.size)
//            firstRandom = false
//            handler.data(listCategory[randomCat].category.toString(), listCategory[randomCat].key.toString())
//        }


        val txtCate = allCategory.category

        holder.binding.txtCategory.text = txtCate

        holder.itemView.setOnClickListener {
            row_index = position
            notifyDataSetChanged()

            val fragment: Fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putString(CAT_KEY, allCategory.key)
            fragment.arguments = bundle

            handler.data(allCategory.category.toString(),allCategory.key.toString())
        }

        if (row_index == position) {
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

    private fun senData(catName: String, catKey: String){
        handler.data(catName, catKey)
    }

    interface Callbacks{
        fun data(catName: String, catKey: String)
    }

    @ColorInt
    fun Context.resolveColorAttr(@AttrRes colorAttr: Int): Int {
        val resolvedAttr = resolveThemeAttr(colorAttr)
        // resourceId is used if it's a ColorStateList, and data if it's a color reference or a hex color
        val colorRes = if (resolvedAttr.resourceId != 0) resolvedAttr.resourceId else resolvedAttr.data
        return ContextCompat.getColor(this, colorRes)
    }

    fun Context.resolveThemeAttr(@AttrRes attrRes: Int): TypedValue {
        val typedValue = TypedValue()
        theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue
    }

    override fun getItemCount(): Int = listCategory.size

    companion object{
        var CAT_KEY = "KEY"
        var CAT_NAME = "NAME"
    }
}