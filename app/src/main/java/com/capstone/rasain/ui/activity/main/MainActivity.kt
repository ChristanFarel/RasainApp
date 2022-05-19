package com.capstone.rasain.ui.activity.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.capstone.rasain.R
import com.capstone.rasain.databinding.ActivityMainBinding
import com.capstone.rasain.ui.fragment.favorite.FavoriteFragment
import com.capstone.rasain.ui.fragment.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager

        val listFragment: ArrayList<Fragment> = arrayListOf(HomeFragment(), FavoriteFragment())

        val pagerAdapter =  PagerAdapter(this, listFragment)

        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false
//        viewPager.currentItem = 0


//        binding.btnToHome.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }

        //SWIPE VIEW PAGER
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeMenu -> viewPager.setCurrentItem(0, false)
                R.id.favoriteMenu -> viewPager.setCurrentItem(1, false)
            }
            true
        }

        setupView()
//        binding.btmNav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.homeMenu ->{
//                    val viewPager = binding.viewPager
//                    viewPager.setCurrentItem(0)
//                    true
//                }
//                R.id.favoriteMenu ->{
//                    val viewPager = binding.viewPager
//                    viewPager.setCurrentItem(1)
//                    true
//                }
//                else -> false
//            }
//        }

    }

    class PagerAdapter(val activity: AppCompatActivity, private val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size

         override fun createFragment(position: Int): Fragment = listFragment[position]
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_nav, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        binding.bottomNav.menu.getItem(2).isEnabled = false
        return true
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    //    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.homeMenu -> {
//                val viewPager = binding.viewPager
//                viewPager.setCurrentItem(0)
//                true
//            }
//            R.id.favoriteMenu -> {
//                val viewPager = binding.viewPager
//                viewPager.setCurrentItem(1)
//                true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}