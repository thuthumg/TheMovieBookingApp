package com.padcmyanmar.ttm.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themoviebookingapp.persistence.typeconverters.CardsTypeConverter
import java.util.regex.Pattern

@Entity(tableName = "UserData")
@TypeConverters(
    CardsTypeConverter::class
)
data class UserDataVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String?,

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    val phone: String?,

    @SerializedName("total_expense")
    @ColumnInfo(name = "total_expense")
    val totalExpense: Int?,

    @SerializedName("profile_image")
    @ColumnInfo(name = "profile_image")
    val profileImage: String?,

    @SerializedName("cards")
    @ColumnInfo(name = "cards")
    val cards: List<CardsVO>?,


    @ColumnInfo(name = "token")
    var token: String?
)