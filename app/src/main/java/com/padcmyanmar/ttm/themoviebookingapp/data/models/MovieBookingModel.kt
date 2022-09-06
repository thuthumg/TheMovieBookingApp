package com.padcmyanmar.ttm.themoviebookingapp.data.models


import com.padcmyanmar.ttm.themoviebookingapp.data.vos.*
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest

interface MovieBookingModel {

    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message:String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (message:String) -> Unit,
        onFailure: (String) -> Unit

    )


    fun getProfile(
        onSuccess: (userDataVO:UserDataVO) -> Unit,
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
        onSuccess : (Pair<Int,String>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getMovieDetail(
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
        movieId: String,
        dateParam:String,
        onSuccess: (List<CinemaDayTimeslotVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSeatingPlan(
        cinemaDayTimeslotId: Int,
        bookingDate:String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethodList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (cardNumber:String, message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        cinemaDayTimeSlotId:Int?,
        rowData:String?,
        seatData:String?,
        movieBookingDateYMDFormat:String?,
        totalPrice:Int?,
        movieId:Int?,
        cinemaId:Int?,
        cardId:Int?,
        snackListString:List<SnackVO>?,
        onSuccess: (checkOutVO:CheckOutVO, message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}