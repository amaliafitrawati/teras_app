package com.bangkit.teras_app.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.pref.User
import com.bangkit.teras_app.data.pref.UserModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: TerasRepository) : ViewModel(){
    val _userSession = mutableStateOf(UserModel(user = User(email = "", address = "", name = ""), token = "", isLogin = false))
    val userSession: State<UserModel> = _userSession

    fun getSession(): Flow<UserModel> {
        return repository.getSession()
    }



}