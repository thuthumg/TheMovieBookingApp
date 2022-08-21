package com.padcmyanmar.ttm.themoviebookingapp.network.dataagents

import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.*
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.CheckOutResponse

interface MovieBookingDataAgent {

    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<String,UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<String,UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(
        token: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logoutCall(
        token: String,
        onSuccess: (Pair<Int,String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        dateParam:String,
        onSuccess: (List<CinemaDayTimeslotVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        dateParam:String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethodList(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (cardNumber:String, message: String) -> Unit,
        onFailure: (String) -> Unit
    )


    fun checkOut(
        token: String,
       checkOutRequest: CheckOutRequest,
        onSuccess: (checkOutVO:CheckOutVO, message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}