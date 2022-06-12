package com.capstone.rasain.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListFavoriteAdapter
import com.capstone.rasain.databinding.FavoriteFragmentBinding

@Suppress("DEPRECATION")
class FavoriteFragment : Fragment() {

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

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[FavoriteViewModel::class.java]

        favoriteViewModel.getAllFav().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                binding.imgFavEmpty.visibility = View.GONE
                binding.txtFavEmpty.visibility = View.GONE
                adapter.setListFav(it)
            }else{
                binding.imgFavEmpty.visibility = View.VISIBLE
                binding.txtFavEmpty.visibility = View.VISIBLE
                adapter.setListFav(it)
            }

        }

        adapter = ListFavoriteAdapter()
        binding.rcyFavorite.setHasFixedSize(true)
        binding.rcyFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyFavorite.adapter = adapter
    }
}