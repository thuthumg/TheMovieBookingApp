package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.*
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.UserDataVO

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userDataVO: UserDataVO)

    @Query("SELECT * from user_data WHERE active_status = 1")
    fun getUserDataByActiveStatus(): UserDataVO?

    /* Updating only active_status
    * By user id
    */
    @Query("UPDATE user_data SET active_status = 0 WHERE  token =:token")
    fun updateUserData(token:String?)

    //@Query("SELECT * from user_data WHERE active_status = 1 AND token != null")
//    @Query("SELECT * from user_data WHERE token = :token ")
//    fun getUserDataByToken(token:String): UserDataVO?


}