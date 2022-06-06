package com.capstone.rasain.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.rasain.databinding.ItemArticleBinding
import com.capstone.rasain.databinding.ItemRecipeHomeBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.response.ResultsItemArticle
import com.capstone.rasain.ui.activity.detail.DetailActivity
import com.capstone.rasain.ui.activity.detailArticle.DetailArticleActivity

class ListArticleAdapter (private val listArticle: ArrayList<ResultsItemArticle>) :
    RecyclerView.Adapter<ListArticleAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allArticle = listArticle[position]

        var title: String? = ""

        for (x in allArticle.key){
            if (x.equals('-')){
                title += " "
            }else{
                title += x
            }
        }

        holder.binding.txtArticle.text = title

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(key,allArticle.key)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listArticle.size

    companion object{
        var key = "KEY"
    }
}
