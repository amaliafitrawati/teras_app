package com.bangkit.teras_app.data.pref


data class UserModel(
    val user: User,
    val token: String,
    val isLogin: Boolean
)

data class User(
    val email: String,
    val address: String,
    val name : String
)