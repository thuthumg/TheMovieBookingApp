package com.padcmyanmar.ttm.themoviebookingapp.network.dataagents

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO
import com.padcmyanmar.ttm.themoviebookingapp.network.TheMovieBookingApi
import com.padcmyanmar.ttm.themoviebookingapp.network.responese.RegisterDataResponse
import com.padcmyanmar.ttm.themoviebookingapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieBookingDataAgent {

    private var mTheMovieBookingApi: TheMovieBookingApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingApi::class.java)

    }

    override fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<String,UserDataVO>, message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.registerWithEmail(
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

                        response.body()?.data?.let {

                            onSuccess(Pair(response.body()?.token ?: "",
                            it),response.body()?.message ?: "")
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


}