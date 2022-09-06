package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.ActorVO


@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(castActorsList: List<ActorVO>,crewActorsList: List<ActorVO>)


    @Query("SELECT * FROM actors")
    fun getAllActors(): List<ActorVO>
}