package com.bangkit.teras_app.ui.screen.register

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.response.RegisterData
import com.bangkit.teras_app.data.response.RegisterResponse
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: TerasRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(UiState.Idle)
    val uiState: StateFlow<UiState<RegisterResponse>> get() = _uiState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun register(name: String, email: String, password: String, province : String) {
        _isLoading.value = true
        val data  = RegisterData(name, email, password, province)
        val client = ApiConfig.api.registerUser(data)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                viewModelScope.launch {
                    try {
                        _isLoading.value = true
                    } catch (e: Exception) {
                        Log.e("Error","Terjadi Kesalahan")
                    } finally {
                        val responseBody = response.body()

                        if(responseBody?.success == 1){
                            _uiState.value = UiState.Success(responseBody)
                        }else{
                            _uiState.value = UiState.Error("Email sudah terdaftar")
                        }

                        _isLoading.value = false
                    }
                }

            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }

}

//    fun register(name: String, email: String, password: String, province : String) {
//        _isLoading.value = true
//        val data  = RegisterData(name, email, password, province)
//        val client = ApiConfig.api.registerUser(data)
//        client.enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//
//                    _uiState.value = UiState.Success(responseBody)
//                    Log.e("Cek Response", "onResponse: ${uiState.value}")
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    val errorMessage = errorBody?.let { JSONObject(it).getString("message") }
//                    Log.e(TAG, "onResponse: $errorMessage")
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//
//        })
//    }
//}