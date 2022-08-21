package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SnackDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.SnackViewHolder

class SnackAdapter(private var onDelegate: SnackDelegate) : RecyclerView.Adapter<SnackViewHolder>() {

    var mSnackList:List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_snack,parent,false)
        return SnackViewHolder(view,onDelegate)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {
        if(mSnackList.isNotEmpty())
        {
            holder.bindData(mSnackList[position])
        }
    }

    override fun getItemCount(): Int {

        return mSnackList.count()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(snackList:List<SnackVO>){
        this.mSnackList = snackList
        notifyDataSetChanged()
    }
}