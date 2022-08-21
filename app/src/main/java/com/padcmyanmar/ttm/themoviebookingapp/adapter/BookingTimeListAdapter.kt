package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.TimeslotsVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.TimeListViewHolder

class BookingTimeListAdapter(private val mDelegate: AvailableTicketDelegate):RecyclerView.Adapter<TimeListViewHolder>() {

   private var mTimeslots:List<TimeslotsVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_booking_time_list,parent,false)

        return TimeListViewHolder(view,mDelegate)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TimeListViewHolder, position: Int) {

        if(mTimeslots.isNotEmpty())
        {
            holder.bindData(mTimeslots[position])

        }
    }

    override fun getItemCount(): Int {
       return mTimeslots.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(timeslots:List<TimeslotsVO>){
        this.mTimeslots = timeslots
        notifyDataSetChanged()
    }
}