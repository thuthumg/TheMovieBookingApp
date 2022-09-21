package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        checkUserDataExistOrNot()

        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, WelcomeLoginActivity::class.java))
            finish()
        }

    }

    private fun checkUserDataExistOrNot() {
        mMovieBookingModel.getProfile(
            paymentFlag = 0,
            onSuccess = { userDataVO ->

                userDataVO.token?.let {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }

            },
            onFailure = {

            }
        )
    }
}