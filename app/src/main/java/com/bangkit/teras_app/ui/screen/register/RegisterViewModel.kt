package com.bangkit.teras_app.ui.screen.register

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.response.RegisterData
import com.bangkit.teras_app.data.response.RegisterResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: TerasRepository) : ViewModel() {
    private val _register = MutableLiveData<RegisterResponse>()
    val register: LiveData<RegisterResponse> = _register

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(name: String, email: String, password: String, province : String) {
        _isLoading.value = true
        Log.e("TEST DATA NEH", province)
        val data  = RegisterData(name, email, password, province)
        val client = ApiConfig.api.registerUser(data)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _register.value = response.body()
                    Log.e("Cek Response", "onResponse: ${_register.value}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let { JSONObject(it).getString("message") }
                    _isLoading.value = false
                    Log.e(TAG, "onResponse: $errorMessage")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}