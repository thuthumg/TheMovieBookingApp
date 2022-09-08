package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters.TimeslotsTypeConverter

@Entity(tableName = "cinemas_timeslot")
@TypeConverters(TimeslotsTypeConverter::class)
data class CinemaDayTimeslotVO(

    @PrimaryKey(autoGenerate = true)
    var cinemaDayTimeslotId: Int = 0 ,

    @SerializedName("cinema_id")
    @ColumnInfo(name = "cinema_id")
    val cinemaId:Int?,

    @SerializedName("cinema")
    @ColumnInfo(name = "cinema")
    val cinema:String?,

    @SerializedName("timeslots")
    @ColumnInfo(name = "timeslots")
    val timeslots:List<TimeslotsVO>?,

    @ColumnInfo(name = "bookingDate")
    var bookingDate:String?


)
