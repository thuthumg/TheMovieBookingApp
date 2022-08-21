package com.padcmyanmar.ttm.themoviebookingapp.network

import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.GetCreditsByMovieResponse
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.GetGenresResponse
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.MovieListResponse
import com.padcmyanmar.ttm.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieListResponse>

    @GET(API_GET_COMING_SOON)
    fun getComingSoonMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieListResponse>

    //movie detail
    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path(PARAM_MOVIE_ID) movieId:String,
        @Query(PARAM_PAGE) page: Int=1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<MovieVO>



    @GET("$API_GET_MOVIE_CREDITS/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path(PARAM_MOVIE_ID) movieId:String,
        @Query(PARAM_PAGE) page: Int=1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<GetCreditsByMovieResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<GetGenresResponse>
}