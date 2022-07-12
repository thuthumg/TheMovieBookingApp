package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.ShowingMovieListViewHolder

class ShowingMovieListAdapter(private val mDelegate:MovieListDelegate) : RecyclerView.Adapter<ShowingMovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowingMovieListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showing_movie_list,parent,false)
        return ShowingMovieListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowingMovieListViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
    return 10
    }
}