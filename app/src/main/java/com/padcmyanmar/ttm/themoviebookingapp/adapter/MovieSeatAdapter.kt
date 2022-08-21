package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.activities.SeatingPlanActivity
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SeatingPlanDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.MovieSeatViewHolder

class MovieSeatAdapter(private val mDelegate: SeatingPlanDelegate
) : RecyclerView.Adapter<MovieSeatViewHolder>() {
    private var mMovieSeats: List<MovieSeatVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_seat,parent,false)
        return MovieSeatViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
      if(mMovieSeats.isNotEmpty()){
          holder.bindData(
              mMovieSeats[position],
            position,
              mMovieSeats.count()
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