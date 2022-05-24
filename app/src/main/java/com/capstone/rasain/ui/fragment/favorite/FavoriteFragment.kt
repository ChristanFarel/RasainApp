package com.capstone.rasain.ui.fragment.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListFavoriteAdapter
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.FavoriteFragmentBinding
import com.capstone.rasain.databinding.HomeFragmentBinding
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.fragment.home.HomeViewModelFragment

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[FavoriteViewModel::class.java]

        favoriteViewModel.getAllFav().observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })

        adapter = ListFavoriteAdapter {
            favoriteViewModel.delFav(it.title)
        }
        binding.rcyFavorite.setHasFixedSize(true)
        binding.rcyFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyFavorite.adapter = adapter

    }

}