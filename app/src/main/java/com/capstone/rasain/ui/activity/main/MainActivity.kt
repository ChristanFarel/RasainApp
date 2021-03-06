package com.capstone.rasain.ui.activity.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.capstone.rasain.R
import com.capstone.rasain.databinding.ActivityMainBinding
import com.capstone.rasain.ui.activity.scan.ScanActivity
import com.capstone.rasain.ui.fragment.article.ArticleFragment
import com.capstone.rasain.ui.fragment.favorite.FavoriteFragment
import com.capstone.rasain.ui.fragment.home.HomeFragment
import com.capstone.rasain.ui.fragment.profile.ProfileFragment
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewPager = binding.viewPager

        val listFragment: ArrayList<Fragment> = arrayListOf(HomeFragment(), FavoriteFragment(),
            ArticleFragment(), ProfileFragment()
        )

        val pagerAdapter =  PagerAdapter(this, listFragment)

        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false
//        viewPager.currentItem = 0


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
                R.id.articleMenu -> viewPager.setCurrentItem(2, false)
                R.id.profileMenu -> viewPager.setCurrentItem(3, false)
            }
            true
        }

        setupView()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.scanMenu.setOnClickListener{
            startActivity(Intent(this, ScanActivity::class.java))
        }
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

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}