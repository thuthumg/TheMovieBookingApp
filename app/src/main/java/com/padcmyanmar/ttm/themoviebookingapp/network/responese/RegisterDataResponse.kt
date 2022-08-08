package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO

data class RegisterDataResponse(

    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("data")
    val data: UserDataVO?
)
