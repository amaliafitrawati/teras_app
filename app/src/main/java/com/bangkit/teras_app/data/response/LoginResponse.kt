package com.bangkit.teras_app.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    val success : Int,
    @SerializedName("message")
    val message : String,
    @SerializedName("token")
    val token : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("address")
    val address : String,
    @SerializedName("email")
    val email : String,
)

data class LoginData(
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("password")
    val password: String,)

