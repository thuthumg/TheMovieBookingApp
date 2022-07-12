package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.DateListViewHolder

class DateListAdapter() :RecyclerView.Adapter<DateListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date_list,parent,false)

    return DateListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateListViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
}