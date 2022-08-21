package com.padcmyanmar.ttm.themoviebookingapp.delegate

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.BookingDate

interface BookingDateDelegate {

   fun onTapDateDelegate(mBookingDate: BookingDate, isSelectedId:Int)
}