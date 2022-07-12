package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.TimeListViewHolder

class BookingTimeListAdapter(private val mDelegate: AvailableTicketDelegate):RecyclerView.Adapter<TimeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_booking_time_list,parent,false)

        return TimeListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: TimeListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 6
    }
}