package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO

@Dao
interface CinemaDayTimeslotDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaDayTimeslot(cinemaDayTimeslot:List<CinemaDayTimeslotVO>)


    @Query("SELECT * FROM cinemas_timeslot WHERE bookingDate = :bookingDate")
    fun getCinemaDayTimeslotByDate(bookingDate:String): List<CinemaDayTimeslotVO>
}