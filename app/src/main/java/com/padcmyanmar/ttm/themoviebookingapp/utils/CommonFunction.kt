package com.padcmyanmar.ttm.themoviebookingapp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO


fun toChangeJsonString(any:Any?):String{
    val gsonPretty = GsonBuilder().setPrettyPrinting().create()
    val strJson: String = gsonPretty.toJson(any)
    println(strJson)
    return strJson
}

fun jsonStringToObjectString(str: String): List<SnackVO> {
    return Gson().fromJson(str, mutableListOf<SnackVO>().javaClass)
}

