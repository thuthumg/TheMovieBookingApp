package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.LogInSignInViewPagerAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInWithEmailDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.RegisterDelegate
import kotlinx.android.synthetic.main.activity_welcome_login.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class WelcomeLoginActivity : AppCompatActivity(), LogInWithEmailDelegate, RegisterDelegate {
    var selectedposition = 0
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpUI()
        setUpClickListener()


    }

    private fun setUpClickListener() {
        tabLayoutLogInSignIn.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                selectedposition = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


    }

    private fun setUpUI() {
        //Set Up View Pager
        val logInSignInViewPagerAdapter = LogInSignInViewPagerAdapter(this)
        viewPagerLogInSignIn.adapter = logInSignInViewPagerAdapter


        //Set Up Tab Layout
        TabLayoutMediator(tabLayoutLogInSignIn, viewPagerLogInSignIn) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.lbl_login)
                1 -> tab.text = getString(R.string.lbl_sign_in)
                else -> tab.text = getString(R.string.lbl_sign_in)
            }
        }.attach()
    }


    private fun logInWithEmailFunction(email: String, password: String) {
        mMovieBookingModel.loginWithEmail(
            email = email,
            password = password,
            onSuccess = {
               // showToast(it)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            },
            onFailure = {
                showToast(it)
            }

        )
    }

    private fun registerWithEmailFunction(
        name: String,
        email: String,
        phone: String,
        password: String
    ) {
        mMovieBookingModel.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                showToast(it)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            },
            onFailure = {
                showToast(it)
            }
        )
    }


    private fun showToast(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }

    override fun onTapLoginWithEmail(email: String, password: String) {
        logInWithEmailFunction(email, password)
    }

    override fun onTapRegister(name: String, phone: String, email: String, password: String) {
        registerWithEmailFunction(name, email, phone, password)
    }

}