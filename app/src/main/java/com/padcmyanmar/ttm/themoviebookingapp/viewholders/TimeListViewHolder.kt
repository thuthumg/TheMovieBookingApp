package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.TimeslotsVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import kotlinx.android.synthetic.main.view_holder_booking_time_list.view.*

class TimeListViewHolder(itemView: View,private val mDelegate: AvailableTicketDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mTimeslotsVO: TimeslotsVO? = null

    init {
        itemView.setOnClickListener {
            mTimeslotsVO?.let { timeSlotVO ->
                mDelegate.onTapTimeSlotClick(timeSlotVO.cinemaDayTimeslotId) }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)
    fun bindData(timeslotsVO: TimeslotsVO) {
        mTimeslotsVO = timeslotsVO

        val strMovieTimeData = timeslotsVO.startTime.toString()?.split(" ")?.toTypedArray()
        itemView.tvStartTime.text = strMovieTimeData?.getOrNull(0)
        itemView.tv12HoursFormat.text = strMovieTimeData?.getOrNull(1)

        if(timeslotsVO.isSelected)
        {
            itemView.llStartTime.background = itemView.context.getDrawable(R.drawable.background_time_button_selected)
            itemView.tvStartTime.setTextColor(itemView.context.getColor(R.color.white))
            itemView.tv12HoursFormat.setTextColor(itemView.context.getColor(R.color.white))
        }else{
            itemView.llStartTime.background = itemView.context.getDrawable(R.drawable.background_time_button)
            itemView.tvStartTime.setTextColor(itemView.context.getColor(R.color.blackLight5))
            itemView.tv12HoursFormat.setTextColor(itemView.context.getColor(R.color.blackLight5))
        }


    }
}