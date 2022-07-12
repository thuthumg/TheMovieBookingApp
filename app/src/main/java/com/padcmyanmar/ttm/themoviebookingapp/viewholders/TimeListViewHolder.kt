package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate

class TimeListViewHolder(itemView: View,private val mDelegate: AvailableTicketDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapClick()
        }
    }
}