package com.bangkit.teras_app.di

import com.bangkit.teras_app.data.RiceProductionRepository

object Injection {
    fun provideRepository() : RiceProductionRepository{
        return RiceProductionRepository.getInstance()
    }
}