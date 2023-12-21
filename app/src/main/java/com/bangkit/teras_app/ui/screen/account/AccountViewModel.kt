package com.bangkit.teras_app.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.pref.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: TerasRepository) : ViewModel(){

    fun getSession(): Flow<UserModel> {
        return repository.getSession()
    }
    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }
}