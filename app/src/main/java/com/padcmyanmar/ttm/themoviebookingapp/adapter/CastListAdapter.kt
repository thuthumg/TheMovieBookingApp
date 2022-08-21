package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.CastListViewHolder

class CastListAdapter : RecyclerView.Adapter<CastListViewHolder>(){

    private var mActorVOs:List<ActorVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast,parent,false)
        return CastListViewHolder(view)

    }

    override fun onBindViewHolder(holder: CastListViewHolder, position: Int) {
        if(mActorVOs.isNotEmpty())
        {
            holder.bindData(mActorVOs[position])
        }
    }

    override fun getItemCount(): Int {
        return mActorVOs.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mActorVOs:List<ActorVO>){
        this.mActorVOs = mActorVOs
        notifyDataSetChanged()
    }
}