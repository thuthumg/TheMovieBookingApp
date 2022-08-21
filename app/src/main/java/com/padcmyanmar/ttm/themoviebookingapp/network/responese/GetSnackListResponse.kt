package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO

data class GetSnackListResponse(

    @SerializedName("code")
    val code:Int?,
    @SerializedName("message")
    val message:String?,
    @SerializedName("data")
    val data:List<SnackVO>?

)
