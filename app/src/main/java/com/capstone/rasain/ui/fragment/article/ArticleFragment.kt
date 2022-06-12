package com.capstone.rasain.ui.fragment.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListArticleAdapter
import com.capstone.rasain.databinding.ArticleFragmentBinding
import com.capstone.rasain.response.ResultsItemArticle

@Suppress("DEPRECATION")
class ArticleFragment : Fragment() {

    private lateinit var articleViewModel: ArticleViewModel
    private var _binding: ArticleFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArticleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[ArticleViewModel::class.java]

        articleViewModel.getListArticle().second.observe(viewLifecycleOwner) {
            setRecycler(it)
        }

        articleViewModel.getListArticle().first.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> binding.progBarArticle.visibility = View.GONE
                is Result.Loading -> binding.progBarArticle.visibility = View.VISIBLE
                is Result.Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRecycler(article: ArrayList<ResultsItemArticle>){
        binding.rcyArticle.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = ListArticleAdapter(article)
        }
    }

}