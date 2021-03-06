package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.adapter.BookingTimeListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import kotlinx.android.synthetic.main.view_holder_ticket_booking_time_item.view.*


class TicketBookingTimeItemViewHolder(itemView: View, var mDelegate: AvailableTicketDelegate) : RecyclerView.ViewHolder(itemView) {
    lateinit var mBookingTimeListAdapter: BookingTimeListAdapter
    init {
        setTimeListRecyclerView()
    }

    private fun setTimeListRecyclerView() {

        mBookingTimeListAdapter = BookingTimeListAdapter(mDelegate)

        itemView.rvAvailableTimeList.adapter = mBookingTimeListAdapter

        itemView.rvAvailableTimeList.layoutManager =
            GridLayoutManager(itemView.context,3)
    }
}