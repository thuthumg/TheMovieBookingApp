package com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO

class CardsTypeConverter {
    @TypeConverter
    fun toString(cards:List<CardsVO>?):String{

        return Gson().toJson(cards)
    }

    @TypeConverter
    fun toCardsVO(cardsVOListJsonString: String):List<CardsVO>?{
        var cardsVOType = object : TypeToken<List<CardsVO>?>(){}.type
        return Gson().fromJson(cardsVOListJsonString,cardsVOType)
    }
}