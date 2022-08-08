package com.padcmyanmar.ttm.themoviebookingapp.network

import com.padcmyanmar.ttm.themoviebookingapp.network.responese.RegisterDataResponse
import com.padcmyanmar.ttm.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {

    @POST(API_REGISTER_WITH_EMAIL)
    @FormUrlEncoded
    fun registerWithEmail(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PHONE) phone: String,
        @Field(PARAM_PASSWORD) password: String
    ): Call<RegisterDataResponse>


}