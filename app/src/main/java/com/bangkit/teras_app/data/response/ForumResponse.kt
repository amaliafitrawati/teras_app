package com.bangkit.teras_app.data.response

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ForumResponse(
	@SerializedName("success")
	val success : Integer,
	@SerializedName("data")
	val data : List<ForumData>
)

@Parcelize
data class ForumData(
	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,
	@field:SerializedName("title")
	val title: String,
	@field:SerializedName("image")
	val image: String? = null,
	@field:SerializedName("createdAt")
	val createdAt: String,
	@field:SerializedName("content")
	val content: String
) : Parcelable

