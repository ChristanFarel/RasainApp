package com.capstone.rasain.ui.fragment.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListFavoriteAdapter
import com.capstone.rasain.databinding.FavoriteFragmentBinding

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
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[FavoriteViewModel::class.java]

        favoriteViewModel.getAllFav().observe(viewLifecycleOwner) {
            adapter.setListFav(it)
        }

//        adapter = ListFavoriteAdapter {
//            favoriteViewModel.delFav(it)
//        }
        adapter = ListFavoriteAdapter()
        binding.rcyFavorite.setHasFixedSize(true)
        binding.rcyFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyFavorite.adapter = adapter

    }

}