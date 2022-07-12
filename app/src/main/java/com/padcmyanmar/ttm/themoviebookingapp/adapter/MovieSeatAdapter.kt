package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.MovieSeatViewHolder

class MovieSeatAdapter( private var mMovieSeats: List<MovieSeatVO> = listOf()) : RecyclerView.Adapter<MovieSeatViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_seat,parent,false)
        return MovieSeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
      if(mMovieSeats.isNotEmpty()){
          holder.bindData(
              mMovieSeats[position]
          )
      }

    }

    override fun getItemCount(): Int {
        return mMovieSeats.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieSeats : List<MovieSeatVO>){
        this.mMovieSeats = movieSeats
        notifyDataSetChanged()
    }
}