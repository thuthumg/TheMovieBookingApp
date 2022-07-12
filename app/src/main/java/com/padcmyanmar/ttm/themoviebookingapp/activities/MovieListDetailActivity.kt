package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.CastListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.ChipGenreAdapter
import kotlinx.android.synthetic.main.activity_movie_list_detail.*

class MovieListDetailActivity : AppCompatActivity() {
    lateinit var mCastListAdapter: CastListAdapter
    lateinit var mChipGenreAdapter: ChipGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_detail)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setUpCastListAdapter()
        setUpChipGenre()
        setUpClickListener()

    }

    private fun setUpChipGenre() {

        mChipGenreAdapter = ChipGenreAdapter()
        rvGenreChip.adapter = mChipGenreAdapter
        rvGenreChip.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpClickListener() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnGetTicket.setOnClickListener {
            startActivity(Intent(this,TicketBookingTimeActivity::class.java))
        }
    }

    private fun setUpCastListAdapter() {
        mCastListAdapter = CastListAdapter()
        rvCastList.adapter = mCastListAdapter
        rvCastList.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }
}