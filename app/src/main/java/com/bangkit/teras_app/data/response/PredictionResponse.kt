package com.bangkit.teras_app.data.response

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PredictionResponse(
    @SerializedName("success")
    val success : Integer,
    @SerializedName("data")
    val data : List<PredictionData>
)

@Parcelize
data class PredictionData(
    @field:SerializedName("province")
    val province: String,
    @field:SerializedName("rank")
    val rank: Int,
    @field:SerializedName("prediction")
    val prediction: Double? = null,
    @field:SerializedName("longitude")
    val longitude: Double? = null,
    @field:SerializedName("latitude")
    val latitude: Double? = null
): Parcelable

