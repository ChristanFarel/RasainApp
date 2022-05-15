package com.capstone.rasain.fragment.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rasain.Repository
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.HomeFragmentBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.retrofit.ApiServiceMasakApa
import com.capstone.rasain.ui.home.HomeViewModel

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModelFragment
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory(Injection.provideRepository(requireContext()))
        )[HomeViewModelFragment::class.java]

        homeViewModel.getNewRecipe().observe(viewLifecycleOwner,{
            setRecycler(it)
        })
    }

    private fun setRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyRecipeFragment.apply {
            layoutManager = LinearLayoutManager(activity)
//            val listUserAdapter = ListRecipeAdapter(recipe)
//            binding.rcyRecipeFragment.adapter = listUserAdapter
            adapter = ListRecipeAdapter(recipe)
        }
    }

}