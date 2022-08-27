package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.BookingDate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.BookingDateDelegate
import kotlinx.android.synthetic.main.view_holder_date_list.view.*

class BookingDateListViewHolder(itemView: View, private val onDelegate:BookingDateDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mbookingDate: BookingDate? = null
   // private var mPosition:Int? = null
    init {
        itemView.setOnClickListener {
            mbookingDate?.let { bookingDate -> onDelegate.onTapDateDelegate(bookingDate,position) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun bindData(bookingDate: BookingDate, position:Int){

        mbookingDate = bookingDate
       // mPosition = position

        itemView.tvDate.text = bookingDate.bookingDate
        itemView.tvDay.text = bookingDate.bookingDay

        if(bookingDate.isSelected)
        {
            itemView.tvDate.setTextColor(itemView.context.getColor(R.color.white))
            itemView.tvDay.setTextColor(itemView.context.getColor(R.color.white))
            itemView.tvDay.textSize = 20F
            itemView.tvDay.setPadding(4,2,4,2)

        }else{
            itemView.tvDate.setTextColor(itemView.context.getColor(R.color.whiteDark7))
            itemView.tvDay.setTextColor(itemView.context.getColor(R.color.whiteDark7))
            itemView.tvDay.textSize = 14F
            itemView.tvDay.setPadding(8)
        }
    }

}
