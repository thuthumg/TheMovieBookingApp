package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.ChipGenreViewHolder

class ChipGenreAdapter : RecyclerView.Adapter<ChipGenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipGenreViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_chip_genre,parent,false)

        return ChipGenreViewHolder(view)

    }

    override fun onBindViewHolder(holder: ChipGenreViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {

        return 5
    }
}