package com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.TimeslotsVO

class TimeslotsTypeConverter {

    @TypeConverter
    fun toString(timeslotsList: List<TimeslotsVO>?):String{
        return Gson().toJson(timeslotsList)
    }

    @TypeConverter
    fun toSpokenLanguage(timeslotsJsonStr: String):List<TimeslotsVO>?{
        var timeslotsListType = object : TypeToken<List<TimeslotsVO>?>(){}.type
        return Gson().fromJson(timeslotsJsonStr,timeslotsListType)
    }
}