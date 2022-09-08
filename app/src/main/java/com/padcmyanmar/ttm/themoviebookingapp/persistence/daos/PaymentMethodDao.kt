package com.padcmyanmar.ttm.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethod(paymentMethodList: List<PaymentMethodVO>)


    @Query("SELECT * FROM payment_method")
    fun getAllPaymentMethods(): List<PaymentMethodVO>
}