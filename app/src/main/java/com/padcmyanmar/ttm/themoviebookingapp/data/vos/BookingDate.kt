package com.padcmyanmar.ttm.themoviebookingapp.data.vos

data class BookingDate (
    var bookingDay:String?, // 1 ,2 ,3 , .........
    var bookingDate:String?,// Mon, Tue , Wed ,...
    var bookingFormatDate:String?, // yyyy-MMM-dd
    var isSelected : Boolean = false
        )
