package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutVO(
    @SerializedName("id")
    var id:Int?,
    @SerializedName("booking_no")
    var bookingNo:String?,
    @SerializedName("booking_date")
    var bookingDate:String?,
    @SerializedName("row")
    var row:String?,
    @SerializedName("seat")
    var seat:String?,
    @SerializedName("total_seat")
    var totalSeat:Int?,
    @SerializedName("total")
    var total:String?,
    @SerializedName("movie_id")
    var movieId:Int?,
    @SerializedName("cinema_id")
    var cinemaId:Int?,
    @SerializedName("username")
    var username:String?,
    @SerializedName("timeslot")
    var timeslot:TimeslotsVO?,
    @SerializedName("snacks")
    var snacks:List<CheckOutSnackVO>?,
    @SerializedName("qr_code")
    var qrCode:String?
)
