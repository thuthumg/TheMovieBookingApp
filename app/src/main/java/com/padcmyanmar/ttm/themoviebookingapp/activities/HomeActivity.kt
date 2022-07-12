package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.ShowingMovieListViewPod
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolBar
import kotlinx.android.synthetic.main.activity_home.viewPodComingSoon
import kotlinx.android.synthetic.main.activity_home.viewPodNowShowing
import kotlinx.android.synthetic.main.activity_movie_list.*

class HomeActivity : AppCompatActivity(), MovieListDelegate {
    lateinit var nowShowingMovieListViewPod : ShowingMovieListViewPod
    lateinit var comingSoonMovieListViewPod : ShowingMovieListViewPod
    var actionBarDrawerToggle : ActionBarDrawerToggle ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpDrawer()
        setUpViewPods()
        setUpClickListener()



    }

    private fun setUpDrawer() {
        setSupportActionBar(toolBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolBar,
            R.string.lbl_open,R.string.lbl_close)
        actionBarDrawerToggle?.let {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }

        toolBar.elevation = 0.0f
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // add leading icon
    }


    private fun setUpViewPods() {
        nowShowingMovieListViewPod = viewPodNowShowing as ShowingMovieListViewPod
        nowShowingMovieListViewPod.setUpMovieListTitleViewPod(titleText = getString(R.string.lbl_now_showing))
        nowShowingMovieListViewPod.setMovieListViewPod(this)

        comingSoonMovieListViewPod = viewPodComingSoon as ShowingMovieListViewPod
        comingSoonMovieListViewPod.setUpMovieListTitleViewPod(titleText = getString(R.string.lbl_coming_soon))
        comingSoonMovieListViewPod.setMovieListViewPod(this)

    }

    private fun setUpClickListener() {
        civProfile.setOnClickListener {
            Snackbar.make(window.decorView,"Profile Picture Tapped",Snackbar.LENGTH_LONG).show()
        }

        llPromotionCode.setOnClickListener {
            Toast.makeText(this,"This is promotion code menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }


        llTranslate.setOnClickListener {
            Toast.makeText(this,"This is translate menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }


        llTermsOfServices.setOnClickListener {
            Toast.makeText(this,"This is term of services menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        llHelp.setOnClickListener {
            Toast.makeText(this,"This is Help Menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        llRateUs.setOnClickListener {
            Toast.makeText(this,"This is Rate Us Menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        llLogout.setOnClickListener {
            Toast.makeText(this,"This is log out", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onTapDetail() {

        startActivity(Intent(this,MovieListDetailActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return super.onCreateOptionsMenu(menu)
    }


}