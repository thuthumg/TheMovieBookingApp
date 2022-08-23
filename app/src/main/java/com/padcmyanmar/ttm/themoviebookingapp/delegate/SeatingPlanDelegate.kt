package com.padcmyanmar.ttm.themoviebookingapp.delegate

interface SeatingPlanDelegate {
    fun onTapSeating(
                     movieSeatId:Int?,
                     movieSeatSymbol:String?,
                     movieSeatSelected:Boolean?,
                     movieSeatPrice:Int?,
                     movieSeatAvailable:Boolean?,
                     positionParam: Int?,
                     movieSeatCount: Int?)
}