package com.capstone.rasain.ui.fragment.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListArticleAdapter
import com.capstone.rasain.adapter.ListFavoriteAdapter
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.ArticleFragmentBinding
import com.capstone.rasain.databinding.FavoriteFragmentBinding
import com.capstone.rasain.response.ResultsItemArticle
import com.capstone.rasain.ui.fragment.favorite.FavoriteViewModel

class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var articleViewModel: ArticleViewModel
    private var _binding: ArticleFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[ArticleViewModel::class.java]

        articleViewModel.getListArticle().second.observe(viewLifecycleOwner,{
            setRecycler(it)
        })

        articleViewModel.getListArticle().first.observe(viewLifecycleOwner,{
            when (it){
                is Result.Success -> binding.progBarArticle.visibility = View.GONE
                is Result.Loading -> binding.progBarArticle.visibility = View.VISIBLE
            }
        })


    }

    private fun setRecycler(article: ArrayList<ResultsItemArticle>){
        binding.rcyArticle.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = ListArticleAdapter(article)
        }
    }

}