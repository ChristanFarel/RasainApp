package com.capstone.rasain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.rasain.databinding.ActivityMainBinding
import com.capstone.rasain.fragment.favorite.FavoriteFragment
import com.capstone.rasain.fragment.home.HomeFragment
import com.capstone.rasain.ui.home.HomeActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewPager = binding.viewPager

        val listFragment: ArrayList<Fragment> = arrayListOf(HomeFragment(), FavoriteFragment())

        var pagerAdapter =  PagerAdapter(this, listFragment)

        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(0)


//        binding.btnToHome.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }

        binding.btmNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    val viewPager = binding.viewPager
                    viewPager.setCurrentItem(0)
                    true
                }
                R.id.favotite ->{
                    val viewPager = binding.viewPager
                    viewPager.setCurrentItem(1)
                    true
                }
                else -> false
            }
        }

    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size

         override fun createFragment(position: Int): Fragment = listFragment.get(position)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                val viewPager = binding.viewPager
                viewPager.setCurrentItem(0)
                true
            }
            R.id.favotite -> {
                val viewPager = binding.viewPager
                viewPager.setCurrentItem(1)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}