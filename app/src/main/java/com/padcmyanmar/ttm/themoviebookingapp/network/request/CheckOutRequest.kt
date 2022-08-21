package com.padcmyanmar.ttm.themoviebookingapp.network.request

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import java.io.Serializable

data class CheckOutRequest(

    @SerializedName("cinema_day_timeslot_id")
    var cinemaDayTimeslotId: Int?,
    @SerializedName("row")
    var row:String?,
    @SerializedName("seat_number")
    var seatNumber:String?,
    @SerializedName("booking_date")
    var bookingDate:String?,
    @SerializedName("total_price")
    var totalPrice:Int?,
    @SerializedName("movie_id")
    var movieId:Int?,
    @SerializedName("card_id")
    var cardId:Int?,
    @SerializedName("cinema_id")
    var cinemaId:Int?,
    @SerializedName("snacks")
    var snacks:List<SnackVO>?
):Serializable
