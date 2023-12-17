package com.bangkit.teras_app.model

import java.util.Date

data class Forum(
    val id: Int,
    val title: String,
    val content: String,
    val image: Int?,
    val date: Date?
)