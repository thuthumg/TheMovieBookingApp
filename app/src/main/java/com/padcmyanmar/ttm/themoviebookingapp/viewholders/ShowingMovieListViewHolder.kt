package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieVO

import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_showing_movie_list.view.*

class ShowingMovieListViewHolder(itemView: View, private val mDelegate:MovieListDelegate) : RecyclerView.ViewHolder(itemView) {


    private var mMovieVO: MovieVO? = null
  //  private var mGenresList:List<GenreVO> = listOf()

    init {

        itemView.setOnClickListener {
            mMovieVO?.let {movie ->
                mDelegate.onTapDetail(movie.id)

            }
        }
    }

    fun bindData(movieVO: MovieVO, mGenresList:List<GenreVO>){
        var genreNameString:String = ""
        mMovieVO = movieVO
        movieVO.genreIds?.let {
            for ((index,genresItem ) in movieVO.genreIds.withIndex()) {
            for (item in mGenresList) {
                    if (genresItem == item.id)
                    {
                         genreNameString += if(index == movieVO.genreIds.size-1)
                            item.name
                        else
                            item.name+"/"

                        break
                    }

                }
            }
        }


        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movieVO.posterPath}")
            .into(itemView.ivShowingMoviePhoto)
        itemView.tvShowingMovieName.text = movieVO.title
        itemView.tvNowShowingMovieTypeAndTime.text = genreNameString
    }
}