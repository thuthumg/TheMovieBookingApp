package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.TicketBookingTimeItemViewHolder

class TicketBookingTimeItemAdapter(private var mDelegate: AvailableTicketDelegate) : RecyclerView.Adapter<TicketBookingTimeItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TicketBookingTimeItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_ticket_booking_time_item,parent,false)

        return TicketBookingTimeItemViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: TicketBookingTimeItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 6
    }
}