package com.bangkit.teras_app.ui.screen.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.pref.User
import com.bangkit.teras_app.data.pref.UserModel
import com.bangkit.teras_app.data.response.LoginData
import com.bangkit.teras_app.data.response.LoginResponse
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: TerasRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Idle)
    val uiState: StateFlow<UiState<LoginResponse>> get() = _uiState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loginUser(email: String, password: String) {
        _isLoading.value = true
        val data  = LoginData(email, password)
        val client = ApiConfig.api.loginUser(data)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                viewModelScope.launch {
                    try {
                        _isLoading.value = true
                    } catch (e: Exception) {
                        Log.e("Error","Terjadi Kesalahan")
                    } finally {
                        val responseBody = response.body()

                        if(responseBody?.success == 1){
                            _uiState.value = UiState.Success(responseBody)
                            val userModel = UserModel(User(responseBody.email, responseBody.address, responseBody.name),
                                responseBody.email, true)
                            saveSession(userModel)
                            Log.e("CEK MODEL", userModel.user.email)
                        }else{
                            _uiState.value = UiState.Error("Email atau password tidak valid")
                        }

                        _isLoading.value = false
                    }
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

}