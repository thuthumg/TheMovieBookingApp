package com.padcmyanmar.ttm.themoviebookingapp.delegate

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO

interface SeatingPlanDelegate {
    fun onTapSeating(movieSeatVO: MovieSeatVO?,positionParam:Int?,movieSeatCount:Int?)
}