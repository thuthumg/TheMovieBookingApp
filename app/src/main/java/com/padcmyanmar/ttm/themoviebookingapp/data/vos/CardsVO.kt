package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CardsVO(

    @SerializedName("id")
    val id: Int?,
    @SerializedName("card_holder")
    val cardHolder: String?,
    @SerializedName("card_number")
    val cardNumber: String?,
    @SerializedName("expiration_date")
    val expirationDate: String?,
    @SerializedName("card_type")
    val cardType: String?

)
