package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SeatingPlanDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(
    itemView: View,
    private val mDelegate: SeatingPlanDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var mMovieSeatVO: MovieSeatVO? = null
    private var position:Int? = null
    private var movieSeatCount:Int? = null

    init {

        itemView.setOnClickListener {
            mMovieSeatVO?.let { it1 -> mDelegate.onTapSeating(it1,position,movieSeatCount) }
//            when {
//                mMovieSeatVO?.isMovieSeatAvailable() == true -> {
//                    itemView.tvMovieSeatTitle.visibility = View.VISIBLE
//                    itemView.tvMovieSeatTitle.text = mMovieSeatVO?.seat_name
//                    itemView.tvMovieSeatTitle.setTextColor(
//                        ContextCompat.getColor(
//                            itemView.context, R.color.white
//                        )
//                    )
//                    itemView.background = ContextCompat.getDrawable(
//                        itemView.context,
//                        R.drawable.background_movie_seat_selection
//                    )
//                }
//
//            }
        }
    }


    fun bindData(data: MovieSeatVO,positionParam:Int,movieSeatCountParam:Int) {
        mMovieSeatVO = data
        position = positionParam
        movieSeatCount = movieSeatCountParam
        when {
            data.isMovieSeatAvailable() -> {
                if(data.isSelected)
                {
                    itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                    itemView.tvMovieSeatTitle.text = mMovieSeatVO?.seat_name
                    itemView.tvMovieSeatTitle.setTextColor(
                        ContextCompat.getColor(
                            itemView.context, R.color.white
                        )
                    )
                    itemView.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.background_movie_seat_selection
                    )
                }
                else{
                    itemView.tvMovieSeatTitle.visibility = View.GONE
                    itemView.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.background_movie_seat_available
                    )
                }

            }

            data.isMovieSeatTaken() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }


            data.isMovieSeatRowTitle() -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )
            }
//
//        data.isMovieSeatEmpty()->{
//
//            itemView.tvMovieSeatTitle.visibility = View.GONE
//            itemView.setBackgroundColor(
//                ContextCompat.getColor(
//                    itemView.context,
//                    R.color.white
//                )
//            )
//        }
//        data.isMovieSeatYourSelection()->{
//            itemView.tvMovieSeatTitle.visibility = View.VISIBLE
//            itemView.tvMovieSeatTitle.text = data.symbol
//            itemView.tvMovieSeatTitle.setTextColor(
//                ContextCompat.getColor(
//                    itemView.context,R.color.white))
//            itemView.background = ContextCompat.getDrawable(
//                itemView.context,
//                R.drawable.background_movie_seat_selection
//            )
//        }

            else -> {
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