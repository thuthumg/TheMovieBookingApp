package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters.CinemaDayTimeslotTypeConverter

@Entity(tableName = "dataCinemaAndTimeSlots")
@TypeConverters(
    CinemaDayTimeslotTypeConverter::class
)
data class DataCinemaAndTimeSlotVO(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date:String = "",

    @ColumnInfo(name = "cinemas")
    var cinemas:List<CinemaDayTimeslotVO>? = null
) {
}