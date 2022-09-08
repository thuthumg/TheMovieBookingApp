package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snacks")
data class SnackVO(

    @SerializedName("id")
    @PrimaryKey
    var id:Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name:String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description:String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price:Int?,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image:String?,

    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    var quantity:Int = 0

){

    fun calculateTotalPayAmt(): Double?
    {
        return price?.toDouble()?.times(quantity)

    }

}