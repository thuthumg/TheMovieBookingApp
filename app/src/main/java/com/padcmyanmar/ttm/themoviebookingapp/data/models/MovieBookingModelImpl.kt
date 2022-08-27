package com.padcmyanmar.ttm.themoviebookingapp.data.models

import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.*
import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.MovieBookingDataAgent
import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.utils.PARAM_BEARER
import com.padcmyanmar.ttm.themoviebookingapp.utils.SUCCESS_CODE

object MovieBookingModelImpl : MovieBookingModel {

    var userToken: String? = null
    private val mMovieBookingDataAgent: MovieBookingDataAgent = RetrofitDataAgentImpl
    override fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = { paramData, message ->

                //process in data layer
                val token = paramData.first
                val userDataVO = paramData.second

                this.userToken = PARAM_BEARER + token

                //to view layer
                onSuccess(message)
            },
            onFailure = onFailure
        )
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.loginWithEmail(
            email = email,
            password = password,
            onSuccess = { paramData, message ->

                //process in data layer
                val token = paramData.first
                val userDataVO = paramData.second

                this.userToken = PARAM_BEARER + token

                //to view layer
                onSuccess(message)
            },
            onFailure = onFailure
        )
    }

    override fun getProfile(
        onSuccess: (userDataVO: UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.userToken?.let {
            mMovieBookingDataAgent.getProfile(
                token = it,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }


    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getComingSoonMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun logoutCall(onSuccess: (Pair<Int, String>) -> Unit, onFailure: (String) -> Unit) {

        this.userToken?.let {
            mMovieBookingDataAgent.logoutCall(
                token = it,
                onSuccess = { successResponse ->
                    if (successResponse.first == SUCCESS_CODE) {
                        this.userToken = null

                    }
                    onSuccess(successResponse)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }


    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDataAgent.getGenres(
            onSuccess = onSuccess, onFailure = onFailure
        )
    }

    override fun getCinemaDayTimeslot(
        movieId: String,
        dateParam: String,
        onSuccess: (List<CinemaDayTimeslotVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.userToken?.let {
            mMovieBookingDataAgent.getCinemaDayTimeslot(
                token = it,
                movieId = movieId,
                dateParam = dateParam,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun getSeatingPlan(
        cinemaDayTimeslotId: Int,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.userToken?.let {
            mMovieBookingDataAgent.getSeatingPlan(
                token = it,
                cinemaDayTimeslotId = cinemaDayTimeslotId.toString(),
                dateParam = bookingDate,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun getSnackList(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        this.userToken?.let {
            mMovieBookingDataAgent.getSnackList(
                token = it,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun getPaymentMethodList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.userToken?.let {
            mMovieBookingDataAgent.getPaymentMethodList(
                token = it,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (cardNumber: String, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        this.userToken?.let {
            mMovieBookingDataAgent.createCard(
                token = it,
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                expirationDate = expirationDate,
                cvc = cvc,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun checkOut(
        cinemaDayTimeSlotId: Int?,
        rowData: String?,
        seatData: String?,
        movieBookingDateYMDFormat: String?,
        totalPrice: Int?,
        movieId: Int?,
        cinemaId: Int?,
        cardId: Int?,
        snackListString: List<SnackVO>?,
        onSuccess: (checkOutVO: CheckOutVO, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {


        var checkOutRequest = CheckOutRequest(
            cinemaDayTimeslotId = cinemaDayTimeSlotId,
            row = rowData,
            seatNumber = seatData,
            bookingDate = movieBookingDateYMDFormat,
            totalPrice = totalPrice,
            movieId = movieId,
            cardId = cardId,
            cinemaId = cinemaId,
            snacks = snackListString
        )

        this.userToken?.let {
            checkOutRequest?.let { it1 ->
                mMovieBookingDataAgent.checkOut(
                    token = it,
                    checkOutRequest = it1,
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            }
        }
    }


}