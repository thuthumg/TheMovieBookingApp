package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import android.util.Log
import com.google.gson.annotations.SerializedName

data class MovieSeatVO(

    @SerializedName("id")
    val id:Int?,
    @SerializedName("seat_name")
    val seat_name:String?,
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("symbol")
    val symbol:String?,
    @SerializedName("price")
    val price:Int?,
    var isSelected:Boolean = false

) {

    fun isMovieSeatAvailable(): Boolean{
        return type == SEAT_TYPE_AVAILABLE
    }

    fun isMovieSeatTaken(): Boolean{
        return type == SEAT_TYPE_TAKEN
    }

    fun isMovieSeatRowTitle(): Boolean{
        return type == SEAT_TYPE_TEXT
    }

//    fun isMovieSeatYourSelection(): Boolean{
//        return type == SEAT_TYPE_SELECTION
//    }

}

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TAKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"
//const val SEAT_TYPE_SELECTION = "selection"