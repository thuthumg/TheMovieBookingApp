package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutSnackVO(

    @SerializedName("id")
    var id:Int?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("price")
    val price:Int?,
    @SerializedName("image")
    val image:String?,
    @SerializedName("quantity")
    var quantity:Int?,
    @SerializedName("unit_price")
    val unitPrice:Int?,
    @SerializedName("total_price")
    var totalPrice:Int?

)
