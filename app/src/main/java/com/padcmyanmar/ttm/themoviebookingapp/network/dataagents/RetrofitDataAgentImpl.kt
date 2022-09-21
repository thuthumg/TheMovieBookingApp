package com.padcmyanmar.ttm.themoviebookingapp.network.dataagents

import android.util.Log

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.*
import com.padcmyanmar.ttm.themoviebookingapp.network.TheMovieApi
import com.padcmyanmar.ttm.themoviebookingapp.network.TheMovieBookingApi
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.*
import com.padcmyanmar.ttm.themoviebookingapp.utils.BASE_URL
import com.padcmyanmar.ttm.themoviebookingapp.utils.CREATE_SUCCESS_CODE
import com.padcmyanmar.ttm.themoviebookingapp.utils.MOVIE_BASE_URL
import com.padcmyanmar.ttm.themoviebookingapp.utils.SUCCESS_CODE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitDataAgentImpl : MovieBookingDataAgent {

    private var mTheMovieBookingApi: TheMovieBookingApi? = null
    private var mTheMovieApi: TheMovieApi? = null


    init {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val mOkHttpClient = OkHttpClient.Builder()

            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitMovieApi = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingApi::class.java)
        mTheMovieApi = retrofitMovieApi.create(TheMovieApi::class.java)
    }

    override fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<String, UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password
        )?.enqueue(
            object : Callback<RegisterDataResponse> {
                override fun onResponse(
                    call: Call<RegisterDataResponse>,
                    response: Response<RegisterDataResponse>
                ) {
                    if (response.isSuccessful) {

                        if (response.body()?.code == CREATE_SUCCESS_CODE) {
                            response.body()?.data?.let {

                                onSuccess(
                                    Pair(
                                        response.body()?.token ?: "",
                                        it
                                    ), response.body()?.message ?: ""
                                )
                            }

                        }
                        else{
                            response.body()?.message?.let { onFailure(it) }
                        }



                    } else {

                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<RegisterDataResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<String, UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginWithEmail(
            email = email,
            password = password
        )?.enqueue(
            object : Callback<RegisterDataResponse> {
                override fun onResponse(
                    call: Call<RegisterDataResponse>,
                    response: Response<RegisterDataResponse>
                ) {
                    if (response.isSuccessful) {

                        Log.d("RetrofitDataAgentImpl", "isSuccessful condition")

                        if (response.body()?.code == SUCCESS_CODE) {
                            response.body()?.data?.let {
                                onSuccess(
                                    Pair(
                                        response.body()?.token ?: "",
                                        it
                                    ), response.body()?.message ?: ""
                                )
                            }
                        } else {
                            response.body()?.message?.let { onFailure(it) }
                        }

                    } else {
                        Log.d("RetrofitDataAgentImpl", "unSuccessful condition $response.message()")

                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<RegisterDataResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getProfile(
        token: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getProfile(
            token = token
        )?.enqueue(
            object : Callback<RegisterDataResponse> {
                override fun onResponse(
                    call: Call<RegisterDataResponse>,
                    response: Response<RegisterDataResponse>
                ) {
                    if (response.isSuccessful) {

                        response.body()?.data?.let {

                            onSuccess(it)
                        }
                    } else {

                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<RegisterDataResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovies()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()

                        onSuccess(movieList)
                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }


    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getComingSoonMovies()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()

                        onSuccess(movieList)
                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            })
    }

    override fun logoutCall(
        token: String,
        onSuccess: (Pair<Int, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.logoutCall(
            token = token
        )?.enqueue(
            object : Callback<RegisterDataResponse> {
                override fun onResponse(
                    call: Call<RegisterDataResponse>,
                    response: Response<RegisterDataResponse>
                ) {
                    if (response.isSuccessful) {

                        Log.d("RetrofitDataAgentImpl", "isSuccessful condition")


                        onSuccess(
                            Pair(
                                response.body()?.code ?: 0,
                                response.body()?.message ?: ""
                            )
                        )
                    } else {
                        Log.d("RetrofitDataAgentImpl", "unSuccessful condition $response.message()")

                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<RegisterDataResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieApi?.getMovieDetails(movieId = movieId)?.enqueue(
            object : Callback<MovieVO> {
                override fun onResponse(
                    call: Call<MovieVO>,
                    response: Response<MovieVO>
                ) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }


    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCreditsByMovie(movieId = movieId)?.enqueue(
            object : Callback<GetCreditsByMovieResponse> {
                override fun onResponse(
                    call: Call<GetCreditsByMovieResponse>,
                    response: Response<GetCreditsByMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.cast ?: listOf())
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetCreditsByMovieResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        mTheMovieApi?.getGenres()?.enqueue(
            object : Callback<GetGenresResponse> {
                override fun onResponse(
                    call: Call<GetGenresResponse>,
                    response: Response<GetGenresResponse>
                ) {

                    if (response.isSuccessful) {
                        onSuccess(response.body()?.genres ?: listOf())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetGenresResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        dateParam: String,
        onSuccess: (List<CinemaDayTimeslotVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieBookingApi?.getCinemaDayTimeslot(
            token = token,
            movieId = movieId,
            date = dateParam
        )?.enqueue(
            object : Callback<GetCinemaDayTimeslotResponse> {
                override fun onResponse(
                    call: Call<GetCinemaDayTimeslotResponse>,
                    response: Response<GetCinemaDayTimeslotResponse>
                ) {

                    if (response.isSuccessful) {
                        onSuccess(response.body()?.cinemaDayTimeslotVO ?: listOf())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetCinemaDayTimeslotResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        dateParam: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSeatingPlan(
            token = token,
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            bookingDate = dateParam
        )?.enqueue(
            object : Callback<GetSeatingPlanResponse> {
                override fun onResponse(
                    call: Call<GetSeatingPlanResponse>,
                    response: Response<GetSeatingPlanResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()?.code == SUCCESS_CODE) {
                            onSuccess(response.body()?.data?.flatten() ?: listOf())

                        } else {
                            response.body()?.message?.let { onFailure(it) }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetSeatingPlanResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnackList(
            token = token
        )?.enqueue(
            object : Callback<GetSnackListResponse> {
                override fun onResponse(
                    call: Call<GetSnackListResponse>,
                    response: Response<GetSnackListResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()?.code == SUCCESS_CODE) {
                            onSuccess(response.body()?.data ?: listOf())

                        } else {
                            response.body()?.message?.let { onFailure(it) }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetSnackListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getPaymentMethodList(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethodList(
            token = token
        )?.enqueue(
            object : Callback<GetPaymentMethodListResponse> {
                override fun onResponse(
                    call: Call<GetPaymentMethodListResponse>,
                    response: Response<GetPaymentMethodListResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()?.code == SUCCESS_CODE) {
                            onSuccess(response.body()?.data ?: listOf())

                        } else {
                            response.body()?.message?.let { onFailure(it) }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetPaymentMethodListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (cardNumber: String, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.createCard(
            token = token,
            cardNumber = cardNumber,
            cardHolder = cardHolder,
            cardExpirationDate = expirationDate,
            cvc = cvc
        )?.enqueue(
            object : Callback<CreatedCardListResponse> {
                override fun onResponse(
                    call: Call<CreatedCardListResponse>,
                    response: Response<CreatedCardListResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()?.code == SUCCESS_CODE) {
                            onSuccess(cardNumber, response.message())

                        } else {
                            response.body()?.message?.let { onFailure(it) }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CreatedCardListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun checkOut(
        token: String,
        checkOutRequest: CheckOutRequest,
        onSuccess: (checkOutVO: CheckOutVO, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieBookingApi?.checkOut(
            token = token,
            checkOutRequest = checkOutRequest
        )?.enqueue(
            object : Callback<CheckOutResponse> {
                override fun onResponse(
                    call: Call<CheckOutResponse>,
                    response: Response<CheckOutResponse>
                ) {

                    if (response.isSuccessful) {
                        if (response.body()?.code == SUCCESS_CODE) {
                            response.body()?.data?.let { onSuccess(it, response.message()) }

                        } else {

                            response.body()?.message?.let { onFailure(it) }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CheckOutResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }


}