package com.bangkit.teras_app.di

import android.content.Context
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.pref.UserPreference
import com.bangkit.teras_app.data.pref.dataStore


object Injection {
    fun provideRepository(context : Context) : TerasRepository{
        val userPreference = UserPreference.getInstance(context.dataStore)
        return TerasRepository.getInstance(userPreference)
    }
}