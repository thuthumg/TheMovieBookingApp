package com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.DataCinemaAndTimeSlotVO

class CinemaDayTimeslotTypeConverter {

    @TypeConverter
    fun toString(dataCinemaAndTimeSlot: List<CinemaDayTimeslotVO>?): String {
        return Gson().toJson(dataCinemaAndTimeSlot)
    }

    @TypeConverter
    fun toCollectionVO(dataCinemaAndTimeSlotListJsonStr: String):  List<CinemaDayTimeslotVO>? {
        var dataCinemaAndTimeSlotVO  = object : TypeToken<List<CinemaDayTimeslotVO>?>(){}.type

        return Gson().fromJson(dataCinemaAndTimeSlotListJsonStr,dataCinemaAndTimeSlotVO)
    }
}