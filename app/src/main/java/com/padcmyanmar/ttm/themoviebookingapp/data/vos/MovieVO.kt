package com.padcmyanmar.ttm.themovieapp.data.vos

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SpokenLanguagesVO

data class MovieVO(

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,


    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("status")
    val status: String?,


    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanieVO>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountrieVO>?,


    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("revenue")
    val revenue: Long?,


    @SerializedName("imdb_id")
    val imdbId: String?,


    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("genres")
    val genres: List<GenreVO>?,


    @SerializedName("budget")
    val budget: Long?,

    @SerializedName("belongs_to_collection")
    val collectionVO: CollectionVO?,
) {
    fun getRatingBasedOnFiveStars(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun calculateMovieTimeMinutesToHours():String{
        val hours: Int = runtime?.div(60) ?: 0

        val minutes: Int = runtime?.rem(60) ?: 0
        return "$hours hr $minutes min"
    }
}

