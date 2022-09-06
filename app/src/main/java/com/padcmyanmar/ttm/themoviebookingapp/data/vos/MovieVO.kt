package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters.*


@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCountryTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    SpokenLanguageTypeConverter::class
)
data class MovieVO(

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,


    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,


    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountries: List<ProductionCountriesVO>?,


    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue: Long?,


    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imdbId: String?,


    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homepage: String?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,


    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    val budget: Long?,

    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val collectionVO: CollectionVO?,

    @ColumnInfo(name = "type")
    var type: String?
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

const val NOW_PLAYING = "NOW_PLAYING"
const val COMING_SOON = "COMING_SOON"

