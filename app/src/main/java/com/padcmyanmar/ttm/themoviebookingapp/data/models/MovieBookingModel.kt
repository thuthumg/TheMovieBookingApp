package com.padcmyanmar.ttm.themoviebookingapp.data.models

interface MovieBookingModel {

    fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message:String) -> Unit,
        onFailure: (String) -> Unit

    )
}