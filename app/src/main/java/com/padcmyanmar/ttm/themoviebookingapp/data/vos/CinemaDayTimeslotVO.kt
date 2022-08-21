package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CinemaDayTimeslotVO(


    @SerializedName("cinema_id")
    val cinemaId:Int?,
    @SerializedName("cinema")
    val cinema:String?,
    @SerializedName("timeslots")
    val timeslots:List<TimeslotsVO>?

)
