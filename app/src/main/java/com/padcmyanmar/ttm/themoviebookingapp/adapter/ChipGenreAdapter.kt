package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.ChipGenreViewHolder

class ChipGenreAdapter : RecyclerView.Adapter<ChipGenreViewHolder>() {

    private var mGenreVOs:List<GenreVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipGenreViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_chip_genre,parent,false)

        return ChipGenreViewHolder(view)

    }

    override fun onBindViewHolder(holder: ChipGenreViewHolder, position: Int) {
        if(mGenreVOs.isNotEmpty())
        {
            holder.bindData(mGenreVOs[position])
        }
    }

    override fun getItemCount(): Int {

        return mGenreVOs.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mGenreVOs:List<GenreVO>){
        this.mGenreVOs = mGenreVOs
        notifyDataSetChanged()


    }
}