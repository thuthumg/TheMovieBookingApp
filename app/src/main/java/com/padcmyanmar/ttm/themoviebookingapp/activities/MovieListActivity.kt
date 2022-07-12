package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.ShowingMovieListViewPod
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() , MovieListDelegate {

    lateinit var nowShowingMovieListViewPod : ShowingMovieListViewPod
    lateinit var comingSoonMovieListViewPod : ShowingMovieListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        // App Bar Leading Icon
        setUpToolbar()
        setUpViewPods()
    }


    private fun setUpToolbar() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // to show leading icon
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // add leading icon
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun setUpViewPods() {
        nowShowingMovieListViewPod = viewPodNowShowing as ShowingMovieListViewPod
        nowShowingMovieListViewPod.setUpMovieListTitleViewPod(titleText = "Now Showing")
        nowShowingMovieListViewPod.setMovieListViewPod(this)

        comingSoonMovieListViewPod = viewPodComingSoon as ShowingMovieListViewPod
        comingSoonMovieListViewPod.setUpMovieListTitleViewPod(titleText = "Coming Soon")
        comingSoonMovieListViewPod.setMovieListViewPod(this)

    }

    override fun onTapDetail() {

        startActivity(Intent(this,MovieListDetailActivity::class.java))
    }


}