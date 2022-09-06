package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class GenreVO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
