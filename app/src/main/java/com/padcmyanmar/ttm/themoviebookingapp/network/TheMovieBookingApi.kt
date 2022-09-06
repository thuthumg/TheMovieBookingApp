package com.padcmyanmar.ttm.themoviebookingapp.network

import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.*
import com.padcmyanmar.ttm.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {

    @POST(API_REGISTER_WITH_EMAIL)
    @FormUrlEncoded
    fun registerUser(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PHONE) phone: String,
        @Field(PARAM_PASSWORD) password: String
    ): Call<RegisterDataResponse>


    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun loginWithEmail(
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String
    ): Call<RegisterDataResponse>


    @GET(API_PROFILE)
    fun getProfile(
        @Header(PARAM_TOKEN) token: String
    ): Call<RegisterDataResponse>

    @POST(API_LOGOUT)
    fun logoutCall(
        @Header(PARAM_TOKEN) token: String
    ): Call<RegisterDataResponse>

    @GET(API_GET_CINEMA_DAY_TIMESLOT)
    fun getCinemaDayTimeslot(
        @Header(PARAM_TOKEN) token: String,
        @Query(PARAM_MOVIE_ID) movieId: String,
        @Query(PARAM_DATE) date:String
    ): Call<GetCinemaDayTimeslotResponse>


    @GET(API_GET_SEAT_PLAN)
    fun getSeatingPlan(
        @Header(PARAM_TOKEN) token: String,
        @Query(PARAM_CINEMA_DAY_TIMESLOT_ID) cinemaDayTimeslotId: String,
        @Query(PARAM_BOOKING_DATE) bookingDate:String
    ): Call<GetSeatingPlanResponse>

    @GET(API_GET_SNACK_LIST)
    fun getSnackList(
        @Header(PARAM_TOKEN) token: String
    ): Call<GetSnackListResponse>

    @GET(API_GET_PAYMENT_METHOD_LIST)
    fun getPaymentMethodList(
        @Header(PARAM_TOKEN) token: String
    ): Call<GetPaymentMethodListResponse>

    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun createCard(
        @Header(PARAM_TOKEN) token: String,
        @Field(PARAM_CARD_NUMBER) cardNumber: String,
        @Field(PARAM_CARD_HOLDER) cardHolder: String,
        @Field(PARAM_EXPIRATION_DATE) cardExpirationDate: String,
        @Field(PARAM_CVC) cvc: String
    ): Call<CreatedCardListResponse>


    @POST(API_CHECK_OUT)
    fun checkOut(
        @Header(PARAM_TOKEN) token: String,
        @Body checkOutRequest: CheckOutRequest): Call<CheckOutResponse>

}