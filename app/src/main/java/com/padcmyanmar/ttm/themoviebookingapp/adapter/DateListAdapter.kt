package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.BookingDate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.BookingDateDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.DateListViewHolder

class DateListAdapter(private val onDelegate: BookingDateDelegate) :RecyclerView.Adapter<DateListViewHolder>(){

    private var mBooingDateList: ArrayList<BookingDate> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date_list,parent,false)

    return DateListViewHolder(view, onDelegate =  onDelegate)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DateListViewHolder, position: Int) {
        if(mBooingDateList.isNotEmpty())
        {
            holder.bindData(mBooingDateList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mBooingDateList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mBooingDateList: ArrayList<BookingDate>)
    {
        this.mBooingDateList = mBooingDateList
        notifyDataSetChanged()
    }
}