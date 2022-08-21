package com.padcmyanmar.ttm.themoviebookingapp.network.responese

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CheckOutVO

data class CheckOutResponse(

    @SerializedName("code")
    var code:Int?,
    @SerializedName("message")
    var message:String?,
    @SerializedName("data")
    var data:CheckOutVO?
)
