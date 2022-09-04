package com.padcmyanmar.ttm.themoviebookingapp

import android.app.Application
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.persistence.MovieBookingDatabase

class MovieBookingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MovieBookingModelImpl.initDatabase(applicationContext)

    }
}