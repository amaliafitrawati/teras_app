package com.bangkit.teras_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.ui.screen.board.BoardViewModel
import com.bangkit.teras_app.ui.screen.home.HomeViewModel
import com.bangkit.teras_app.ui.screen.login.LoginViewModel
import com.bangkit.teras_app.ui.screen.register.RegisterViewModel
import com.bangkit.teras_app.ui.screen.story.StoryViewModel

class ViewModelFactory(
    private val riceRepository: RiceProductionRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(riceRepository) as T
        } else if (modelClass.isAssignableFrom(BoardViewModel::class.java)) {
            return BoardViewModel(riceRepository) as T
        }else if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
            return StoryViewModel(riceRepository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(riceRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(riceRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}