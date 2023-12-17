package com.bangkit.teras_app.data.api

import retrofit2.Retrofit

import com.bangkit.teras_app.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    //private const val BASE_URL = "https://teras-backend-hqlboaqepq-et.a.run.app"

//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val apiService : ApiService by lazy {
//        retrofit.create(ApiService::class.java)
//    }

    companion object{
        private const val BASE_URL = BuildConfig.BASE_URL
        val api: ApiService by lazy {
            val loggingInterceptor =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

//    companion object{
//        const val BASE_URL = BuildConfig.BASE_URL
//
//        fun getApiService(): ApiService {
//            val loggingInterceptor =
//                if (BuildConfig.DEBUG) {
//                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//                } else {
//                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//                }
//            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
}