package com.padcmyanmar.ttm.themoviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO
import com.padcmyanmar.ttm.themoviebookingapp.persistence.daos.UserDataDao

@Database(entities = [UserDataVO::class], version = 1, exportSchema = false)
abstract class MovieBookingDatabase: RoomDatabase() {

    companion object{
        const val DB_NAME = "THE_MOVIE_BOOKING_DB"

        var dbInstance:MovieBookingDatabase? = null

        fun getDBInstance(context:Context):MovieBookingDatabase?{

            when(dbInstance){
                null->{
                    dbInstance = Room.databaseBuilder(context,MovieBookingDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                }
            }

            return dbInstance
        }

    }

    abstract fun userDataDao():UserDataDao
}