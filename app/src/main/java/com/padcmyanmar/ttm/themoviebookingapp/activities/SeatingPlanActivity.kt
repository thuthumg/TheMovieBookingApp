package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.MovieSeatAdapter
import com.padcmyanmar.ttm.themoviebookingapp.dummy.DUMMY_SEATS
import kotlinx.android.synthetic.main.activity_seating_plan.*


class SeatingPlanActivity : AppCompatActivity() {

    private val mMovieSeatAdapter: MovieSeatAdapter = MovieSeatAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpSeatingPlan()
        setUpClickListener()

    }

    private fun setUpClickListener() {
        btnBuyTicket.setOnClickListener {
            startActivity(Intent(this, SnackActivity::class.java))
        }

        btnSeatingPlanBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpSeatingPlan() {
        rvSeatingPlan.adapter = mMovieSeatAdapter
        rvSeatingPlan.layoutManager = GridLayoutManager(applicationContext,10)

        mMovieSeatAdapter.setNewData(DUMMY_SEATS)
    }


}