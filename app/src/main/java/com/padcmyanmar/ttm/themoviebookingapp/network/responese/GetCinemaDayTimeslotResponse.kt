package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO

data class GetCinemaDayTimeslotResponse(

    @SerializedName("code")
    val code:Int?,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val cinemaDayTimeslotVO:List<CinemaDayTimeslotVO>?

)
