package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.BookingTimeObjectViewHolder

class BookingTimeObjectAdapter(private var mDelegate: AvailableTicketDelegate) : RecyclerView.Adapter<BookingTimeObjectViewHolder>() {

    private var mCinemaDayTimeslotVOs:List<CinemaDayTimeslotVO> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingTimeObjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_ticket_booking_time_item,parent,false)

        return BookingTimeObjectViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BookingTimeObjectViewHolder, position: Int) {

        if(mCinemaDayTimeslotVOs.isNotEmpty())
        {
            holder.bindData(mCinemaDayTimeslotVOs[position])
        }

    }

    override fun getItemCount(): Int {
       return mCinemaDayTimeslotVOs.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cinemaDayTimeslotVOs:List<CinemaDayTimeslotVO>){
        this.mCinemaDayTimeslotVOs = cinemaDayTimeslotVOs
        notifyDataSetChanged()
    }
}