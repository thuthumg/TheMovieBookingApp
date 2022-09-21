package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.DataCinemaAndTimeSlotVO

@Dao
interface DataCinemaAndTimeSlotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaAndTimeslot(cinemaDataAndTimeslot:DataCinemaAndTimeSlotVO)


    @Query("SELECT * FROM dataCinemaAndTimeSlots WHERE date = :bookingDate")
    fun getCinemaAndTimeslotByDate(bookingDate:String): DataCinemaAndTimeSlotVO
}