package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class SnackVO(

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
    var quantity:Int = 0

){

    fun calculateTotalPayAmt(): Double?
    {
        return price?.toDouble()?.times(quantity)

    }

}