package com.padcmyanmar.ttm.themoviebookingapp.data.models

import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.MovieBookingDataAgent
import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl

object MovieBookingModelImpl : MovieBookingModel {

    var userToken: String? = null
    private val mMovieBookingDataAgent: MovieBookingDataAgent = RetrofitDataAgentImpl
    override fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = { paramData, message ->

                //process in data layer
                val token = paramData.first
                val userDataVO = paramData.first

                this.userToken = token

                //to view layer
                onSuccess(message)
            },
            onFailure = onFailure
        )
    }


}