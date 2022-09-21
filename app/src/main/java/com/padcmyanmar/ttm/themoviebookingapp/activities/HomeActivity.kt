package com.padcmyanmar.ttm.themoviebookingapp.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu

import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide

import com.google.android.material.snackbar.Snackbar


import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.utils.BASE_URL
import com.padcmyanmar.ttm.themoviebookingapp.utils.SUCCESS_CODE
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.ShowingMovieListViewPod
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolBar
import kotlinx.android.synthetic.main.activity_home.viewPodComingSoon
import kotlinx.android.synthetic.main.activity_home.viewPodNowShowing


class HomeActivity : AppCompatActivity(), MovieListDelegate {


    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var nowShowingMovieListViewPod: ShowingMovieListViewPod
    lateinit var comingSoonMovieListViewPod: ShowingMovieListViewPod
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var mGenres: List<GenreVO> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpDrawer()
        setUpViewPods()
        setUpClickListener()
        requestData()


    }

    private fun requestData() {
        mMovieBookingModel.getProfile(
            paymentFlag = 0,
            onSuccess = { userDataVO ->

                bindDataAtNavigationView(userDataVO)
                bindDataAtMainContent(userDataVO)

            },
            onFailure = {
                showToast(it)
            }
        )
        //get genres
        mMovieBookingModel.getGenres(
            onSuccess = {
                mGenres = it
                Log.d("home", "check genres list = " + mGenres.size)
                nowShowingMovieListViewPod.setGenresData(mGenres)
                comingSoonMovieListViewPod.setGenresData(mGenres)
            },
            onFailure = {
                showToast(it)
            }
        )

        //Now Playing Movies
        mMovieBookingModel.getNowPlayingMovies(
            onSuccess = {
                nowShowingMovieListViewPod.setData(it)

            },
            onFailure = {
                // show error message
                showToast(it)
            }
        )

        //Coming Soon Movies
        mMovieBookingModel.getComingSoonMovies(
            onSuccess = {
                comingSoonMovieListViewPod.setData(it)

            },
            onFailure = {
                // show error message
                showToast(it)
            }
        )


    }

    private fun bindDataAtMainContent(userDataVO: UserDataVO) {
        Glide.with(this)
            .load("$BASE_URL${userDataVO.profileImage}")
            .placeholder(R.drawable.pic_profile)
            .into(civMainContentProfile)

        tvMainContentProfileName.text = userDataVO.name


    }

    private fun bindDataAtNavigationView(userDataVO: UserDataVO) {
        tvName.text = userDataVO.name
        tvEmail.text = userDataVO.email

        Glide.with(this)
            .load("$BASE_URL${userDataVO.profileImage}")
            .placeholder(R.drawable.pic_profile)
            .into(civProfile)
    }

    private fun setUpDrawer() {
        setSupportActionBar(toolBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolBar,
            R.string.lbl_open, R.string.lbl_close
        )
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
            Snackbar.make(window.decorView, "Profile Picture Tapped", Snackbar.LENGTH_LONG).show()
        }

        llPromotionCode.setOnClickListener {
            Toast.makeText(this, "This is promotion code menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }


        llTranslate.setOnClickListener {
            Toast.makeText(this, "This is translate menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }


        llTermsOfServices.setOnClickListener {
            Toast.makeText(this, "This is term of services menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        llHelp.setOnClickListener {
            Toast.makeText(this, "This is Help Menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        llRateUs.setOnClickListener {
            Toast.makeText(this, "This is Rate Us Menu", Toast.LENGTH_SHORT).show()
            //Logic
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        //for logout
        llLogout.setOnClickListener {
            logOutAlert()
        }
    }

    private fun logoutFunction() {
        mMovieBookingModel.logoutCall(
            onSuccess = { successResponse ->
                if (successResponse.first == SUCCESS_CODE) {
                    startActivity(Intent(this, WelcomeLoginActivity::class.java))
                    finish()
                    //  showToast(successResponse.second)
                } else {
                    showToast(successResponse.second)
                }
            },
            onFailure = { failMsg ->
                showToast(failMsg)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showToast(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }

    override fun onTapDetail(movieId: Int) {
        startActivity(
            MovieListDetailActivity.newIntent(
                context = this,
                movieId = movieId
            )
        )
    }

    private fun logOutAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert!")
        builder.setMessage("Do you want to exit?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
            logoutFunction()
            dialog.cancel()
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
            dialog.cancel()
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        builder.show()
    }
}