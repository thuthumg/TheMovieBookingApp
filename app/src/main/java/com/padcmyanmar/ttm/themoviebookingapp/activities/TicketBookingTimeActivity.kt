package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.TicketBookingTimeItemAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.DateListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.TicketTypeDelegate
import kotlinx.android.synthetic.main.activity_ticket.*


class TicketBookingTimeActivity : AppCompatActivity(), AvailableTicketDelegate, TicketTypeDelegate {

    lateinit var mDateListAdapter: DateListAdapter
    lateinit var mTicketBookingTimeItemAdapter: TicketBookingTimeItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpDateListAdapter()
        setUpAvailableTicketItemAdapter()
        setUpClickListener()
    }

    private fun setUpAvailableTicketItemAdapter() {
        mTicketBookingTimeItemAdapter = TicketBookingTimeItemAdapter(this)
        rvAvailableTicketItemList.adapter = mTicketBookingTimeItemAdapter
        rvAvailableTicketItemList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
    private fun setUpClickListener() {
        btnNext.setOnClickListener {
            startActivity(Intent(this, SeatingPlanActivity::class.java))
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpDateListAdapter() {
        mDateListAdapter = DateListAdapter()
        rvDateList.adapter = mDateListAdapter
        rvDateList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onTapClick() {
        Toast.makeText(this,"Time Click Event",Toast.LENGTH_SHORT).show()
    }

    override fun onTapTypeClick() {
        Toast.makeText(this,"Available Type Click Event",Toast.LENGTH_SHORT).show()

    }


}
