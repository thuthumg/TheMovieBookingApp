package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.GenreVO


data class GetGenresResponse(

    @SerializedName("genres")
    val genres: List<GenreVO>?
)