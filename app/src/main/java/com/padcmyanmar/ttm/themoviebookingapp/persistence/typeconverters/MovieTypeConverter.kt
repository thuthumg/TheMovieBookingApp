package com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieVO

class MovieTypeConverter {

    @TypeConverter
    fun toString(moviesList: List<MovieVO>?): String {
        return Gson().toJson(moviesList)
    }

    @TypeConverter
    fun toGenreList(moviesListJsonStr: String): List<MovieVO>? {
        var moviesListType  = object : TypeToken<List<MovieVO>?>(){}.type

        return Gson().fromJson(moviesListJsonStr,moviesListType)
    }
}