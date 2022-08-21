package com.padcmyanmar.ttm.themoviebookingapp.delegate

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO

interface SnackDelegate {
    fun onTapMinus(snackVO: SnackVO?)
    fun onTapPlus(snackVO:SnackVO?)
}