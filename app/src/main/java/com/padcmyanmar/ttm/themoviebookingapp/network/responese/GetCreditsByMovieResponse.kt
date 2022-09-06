package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.ActorVO


data class GetCreditsByMovieResponse (
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?
        )


