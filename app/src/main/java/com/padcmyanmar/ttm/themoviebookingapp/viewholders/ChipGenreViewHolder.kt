package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.GenreVO

import kotlinx.android.synthetic.main.view_holder_chip_genre.view.*


class ChipGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mGenreVO: GenreVO? = null


    fun bindData(genreVO: GenreVO){

        mGenreVO = genreVO


        itemView.chipName.text = genreVO.name
    }
}