package com.bangkit.teras_app.data.api

import com.bangkit.teras_app.data.response.ForumResponse
import com.bangkit.teras_app.data.response.PredictionData
import com.bangkit.teras_app.data.response.PredictionResponse
import com.bangkit.teras_app.data.response.RegisterData
import com.bangkit.teras_app.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("forum")
    fun getForum() : Call<ForumResponse>

    @POST("register")
    fun registerUser(
        @Body request: RegisterData
    ): Call<RegisterResponse>

    @GET("prediction")
    fun getPrediction() : Call<PredictionResponse>
}