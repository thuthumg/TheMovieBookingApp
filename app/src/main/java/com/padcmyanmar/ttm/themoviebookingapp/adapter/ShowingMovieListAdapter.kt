package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.ShowingMovieListViewHolder

class ShowingMovieListAdapter(private val mDelegate:MovieListDelegate) : RecyclerView.Adapter<ShowingMovieListViewHolder>() {
    private var mMovieList : List<MovieVO> = arrayListOf()
    private var mGenresList : List<GenreVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowingMovieListViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showing_movie_list,parent,false)
        return ShowingMovieListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowingMovieListViewHolder, position: Int) {

        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position],mGenresList)
        }
    }

    override fun getItemCount(): Int {
    return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }

    fun setNewGenreData(mGenres: List<GenreVO>)
    {
        mGenresList = mGenres
    }
}