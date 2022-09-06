package com.padcmyanmar.ttm.themoviebookingapp.data.models

import android.content.Context

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.*
import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.MovieBookingDataAgent
import com.padcmyanmar.ttm.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.persistence.MovieBookingDatabase
import com.padcmyanmar.ttm.themoviebookingapp.utils.PARAM_BEARER
import com.padcmyanmar.ttm.themoviebookingapp.utils.SUCCESS_CODE

object MovieBookingModelImpl : MovieBookingModel {

    var userToken: String? = null

    //Data Agent Dependency
    private val mMovieBookingDataAgent: MovieBookingDataAgent = RetrofitDataAgentImpl

    //Database Dependency
    private var mMovieBookingDataBase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieBookingDataBase = MovieBookingDatabase.getDBInstance(context)
    }

    override fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //Network
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

                userDataVO.token = this.userToken

                mMovieBookingDataBase?.userDataDao()?.insertUserData(
                    userDataVO = userDataVO
                )


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



                userDataVO.token = this.userToken

                mMovieBookingDataBase?.userDataDao()?.insertUserData(
                    userDataVO = userDataVO
                )

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


        //Database
        //  this.userToken?.let {

        when (this.userToken?.isEmpty()) {
            null -> {
                onFailure("Fail")

            }
            else -> {

                this.userToken?.let {
                    mMovieBookingDataBase?.userDataDao()?.getUserDataByToken(
                        token = it
                    )?.let { it1 -> onSuccess(it1) }
                }
            }


        }


        //  }


        //Network

//        this.userToken?.let {
//            mMovieBookingDataAgent.getProfile(
//                token = it,
//                onSuccess = onSuccess,
//                onFailure = onFailure
//            )
//        }
    }


    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //Database
        onSuccess(
            mMovieBookingDataBase?.movieDao()?.getMovieByType(
                type = NOW_PLAYING
            ) ?: listOf()
        )


        //Network
        mMovieBookingDataAgent.getNowPlayingMovies(onSuccess = {

            it.forEach { movieVO -> movieVO.type = NOW_PLAYING }
            mMovieBookingDataBase?.movieDao()?.insertMovies(it)

            onSuccess(it)

        }, onFailure = onFailure)
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //Database
        onSuccess(
            mMovieBookingDataBase?.movieDao()?.getMovieByType(
                type = COMING_SOON
            ) ?: listOf()
        )


        //Network
        mMovieBookingDataAgent.getComingSoonMovies(onSuccess = {

            it.forEach { movieVO -> movieVO.type = COMING_SOON }
            mMovieBookingDataBase?.movieDao()?.insertMovies(it)

            onSuccess(it)


        }, onFailure = onFailure)
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

        //Database

        mMovieBookingDataBase?.movieDao()?.getMovieById(
            movieId =
            movieId.toInt()
        )?.let { onSuccess(it) }


        //Network
        mMovieBookingDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = {
                val movieBookingDatabase = mMovieBookingDataBase?.movieDao()?.getMovieById(
                    movieId =
                    movieId.toInt()
                )

                it.type = movieBookingDatabase?.type

                mMovieBookingDataBase?.movieDao()?.insertSingleMovies(it)

                onSuccess(it)


            },
            onFailure = onFailure
        )
    }


    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //Database
      //  onSuccess(mMovieBookingDataBase?.actorDao()?.getAllActors() ?: listOf())


        //Network
        mMovieBookingDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = {
                   mMovieBookingDataBase?.actorDao()?.insertActors(it.first,it.second)
                        onSuccess(it)

            },
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