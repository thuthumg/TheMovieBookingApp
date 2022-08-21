package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.util.regex.Pattern

data class UserDataVO (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("total_expense")
    val totalExpense: Int?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("cards")
    val cards: List<CardsVO>?
)