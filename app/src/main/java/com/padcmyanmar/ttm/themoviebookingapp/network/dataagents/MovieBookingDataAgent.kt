package com.padcmyanmar.ttm.themoviebookingapp.network.dataagents

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO

interface MovieBookingDataAgent {

    fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<String,UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}