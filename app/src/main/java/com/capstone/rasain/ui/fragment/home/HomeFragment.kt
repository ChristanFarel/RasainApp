package com.capstone.rasain.ui.fragment.home

import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.rasain.R
import com.capstone.rasain.Result
import com.capstone.rasain.ViewModelFactory
import com.capstone.rasain.adapter.ListCategoryAdapter
import com.capstone.rasain.adapter.ListRecipeAdapter
import com.capstone.rasain.databinding.HomeFragmentBinding
import com.capstone.rasain.response.ResultsCategory
import com.capstone.rasain.response.ResultsItem
import com.capstone.rasain.ui.activity.SearchResultActivity
import com.capstone.rasain.ui.activity.login.LoginActivity
import com.capstone.rasain.ui.activity.main.MainActivity
import kotlin.random.Random

@Suppress("DEPRECATION")
class HomeFragment : Fragment(), ListCategoryAdapter.Callbacks {


    companion object {
        fun newInstance() = HomeFragment()
        var HOMEFOOD = "HOMEFOOD"
        var VIEWALLNEW = "VIEWALLNEW"
    }

    private lateinit var homeViewModel: HomeViewModelFragment
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        )[HomeViewModelFragment::class.java]

//        homeViewModel.getNewRecipe().second.observe(viewLifecycleOwner) {
//            setFoodRecycler(it)
//        }

        homeViewModel.getNewRecipeWithLimit(4).second.observe(viewLifecycleOwner) {
            setFoodRecycler(it)
        }

        homeViewModel.getNewRecipe().first.observe(viewLifecycleOwner) {
            when(it){
                is Result.Loading -> binding.progBarHome.visibility = View.VISIBLE
                is Result.Success -> binding.progBarHome.visibility = View.GONE
            }
        }

        homeViewModel.getCategory().observe(viewLifecycleOwner){
            setCateRecycler(it)
        }

        binding.btnLogout.setOnClickListener {
            alertLogout()
        }

        homeViewModel.getUser().observe(viewLifecycleOwner){
            binding.tvUser.text = resources.getString(R.string.welcome_user, it.fullName)
        }

        binding.searchButton.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(requireContext(),SearchResultActivity::class.java)
                intent.putExtra(HOMEFOOD, query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        binding.txtViewAllNewRecipe.setOnClickListener {
            val intent = Intent(requireContext(),SearchResultActivity::class.java)
            intent.putExtra(VIEWALLNEW, "ViewAll")
            startActivity(intent)
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
            adapter = ListCategoryAdapter(category, this@HomeFragment)
            val randomCat = Random.nextInt(0, category.size)
            data(category[randomCat].category.toString(), category[randomCat].key.toString())
        }
    }

    private fun setRecipeByCateRecycler(recipe: ArrayList<ResultsItem>){
        binding.rcyFoodCategory.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = ListRecipeAdapter(recipe)
        }
    }

    private fun alertLogout() {
        val alertLogout = AlertDialog.Builder(requireContext())
        with(alertLogout) {
            setTitle(resources.getString(R.string.title_logout_confirmation))
            setMessage(resources.getString(R.string.logout_confirmation))
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                homeViewModel.logout()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                activity?.finish()
            }
            setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertLogout.create()
        alertDialog.show()
    }

    override fun data(catName: String, catKey: String) {
        homeViewModel.getRecipeByCate(catKey).observe(viewLifecycleOwner){
            setRecipeByCateRecycler(it)
            binding.tvFoodCategory.text = catName
        }
    }
}