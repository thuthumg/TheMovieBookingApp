package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate

class ShowingMovieListViewHolder(itemView: View, private val mDelegate:MovieListDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mDelegate.onTapDetail()
        }
    }
}