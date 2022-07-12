package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

fun bindData(data: MovieSeatVO){

    when{
        data.isMovieSeatAvailable() ->{
            itemView.tvMovieSeatTitle.visibility = View.GONE
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_movie_seat_available
            )
        }

        data.isMovieSeatTaken()->{
            itemView.tvMovieSeatTitle.visibility = View.GONE
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_movie_seat_taken
            )
        }



        data.isMovieSeatRowTitle() ->{
            itemView.tvMovieSeatTitle.visibility = View.VISIBLE
            itemView.tvMovieSeatTitle.text = data.title
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,R.color.white))
        }


        data.isMovieSeatYourSelection()->{
            itemView.tvMovieSeatTitle.visibility = View.VISIBLE
            itemView.tvMovieSeatTitle.text = data.title
            itemView.tvMovieSeatTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,R.color.white))
            itemView.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_movie_seat_selection
            )
        }

        else ->{
            itemView.tvMovieSeatTitle.visibility = View.GONE
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )
        }

    }
}


}