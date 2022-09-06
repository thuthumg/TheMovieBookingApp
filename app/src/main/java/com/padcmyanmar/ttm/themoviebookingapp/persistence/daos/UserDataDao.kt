package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userDataVO: UserDataVO)

    @Query("SELECT * from user_data")
    fun getAllUsersData(): List<UserDataVO>

    @Query("SELECT * from user_data WHERE token = :token")
    fun getUserDataByToken(token:String): UserDataVO?


}