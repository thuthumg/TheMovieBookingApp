package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovies(movie: MovieVO?)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId:Int): MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieByType(type:String): List<MovieVO>


    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}