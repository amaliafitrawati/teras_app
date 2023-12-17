package com.bangkit.teras_app.data.api

import com.bangkit.teras_app.data.response.ForumResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("forum")
    fun getForum() : Call<ForumResponse>
}