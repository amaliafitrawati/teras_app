package com.bangkit.teras_app.model

data class RiceProduction(
    val id: Int,
    val province: String,
    val ranking: Int,
    val totalProduction: Float,
    val year: Int,
    val isSurplus: Boolean = false
)