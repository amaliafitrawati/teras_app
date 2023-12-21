package com.bangkit.teras_app.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RegisterResponse(
    @SerializedName("success")
    val success : Int,
    @SerializedName("message")
    val message: String
)


data class RegisterData(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("password")
    val password: String,
    @field:SerializedName("address")
    val address: String,)

@Parcelize
data class RegisterReturnData(
    @field:SerializedName("fieldCount")
    val fieldCount: String,
    @field:SerializedName("affectedRows")
    val affectedRows: String,
    @field:SerializedName("insertId")
    val insertId: String,
    @field:SerializedName("serverStatus")
    val serverStatus: String,
    @field:SerializedName("warningCount")
    val warningCount: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("protocol41")
    val protocol41: String,
    @field:SerializedName("changedRows")
    val changedRows: String): Parcelable