package com.padcmyanmar.ttm.themoviebookingapp.data.vos

class MovieSeatVO(val title: String = "",
                  private val type: String = "") {


    fun isMovieSeatAvailable(): Boolean{
        return type == SEAT_TYPE_AVAILABLE
    }

    fun isMovieSeatTaken(): Boolean{
        return type == SEAT_TYPE_TAKEN
    }

    fun isMovieSeatRowTitle(): Boolean{
        return type == SEAT_TYPE_TEXT
    }

    fun isMovieSeatYourSelection(): Boolean{
        return type == SEAT_TYPE_SELECTION
    }
}

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TAKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"
const val SEAT_TYPE_SELECTION = "selection"