package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.LogInSignInViewPagerAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import kotlinx.android.synthetic.main.activity_welcome_login.*

class WelcomeLoginActivity : AppCompatActivity() , LogInSignInDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //Set Up View Pager
        val logInSignInViewPagerAdapter = LogInSignInViewPagerAdapter(this,this)
        viewPagerLogInSignIn.adapter =logInSignInViewPagerAdapter


        //Set Up Tab Layout
        TabLayoutMediator(tabLayoutLogInSignIn,viewPagerLogInSignIn){tab,position->
            when(position)
            {
                0-> tab.text= getString(R.string.lbl_login)
                1-> tab.text = getString(R.string.lbl_sign_in)
                else-> tab.text = getString(R.string.lbl_sign_in)
            }
        }.attach()
    }

    override fun onTapGoToMovieListPage() {


       startActivity(Intent(this, HomeActivity::class.java))
    }


}