package com.capstone.rasain.ui.fragment.home

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListCategoryAdapter
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.HomeFragmentBinding
import com.capstone.rasain.di.Injection
import com.capstone.rasain.response.ResultsCategory
import com.capstone.rasain.response.ResultsItem

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
            ViewModelFactory(requireContext())
        )[HomeViewModelFragment::class.java]

        homeViewModel.getNewRecipe().observe(viewLifecycleOwner) {
            setFoodRecycler(it)
        }

        homeViewModel.getCategory().observe(viewLifecycleOwner){
            setCateRecycler(it)
        }
    }

    private fun setFoodRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFood.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//            val listUserAdapter = ListRecipeAdapter(recipe)
//            binding.rcyRecipeFragment.adapter = listUserAdapter
            adapter = ListRecipeAdapter(recipe)
        }


    }

    private fun setCateRecycler(category: ArrayList<ResultsCategory>){
        binding.rcyCategory.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = ListCategoryAdapter(category)
        }
    }

}