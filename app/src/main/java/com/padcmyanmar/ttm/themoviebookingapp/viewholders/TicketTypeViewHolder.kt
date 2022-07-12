package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.delegate.TicketTypeDelegate


class TicketTypeViewHolder (itemView: View, private val mDelegate: TicketTypeDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapTypeClick()
        }
    }
}