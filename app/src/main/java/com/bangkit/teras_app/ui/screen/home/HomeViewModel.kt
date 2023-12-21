package com.bangkit.teras_app.ui.screen.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.calculateDistance
import com.bangkit.teras_app.data.pref.UserModel
import com.bangkit.teras_app.data.response.PredictionData
import com.bangkit.teras_app.data.response.PredictionResponse
import com.bangkit.teras_app.ui.common.UiState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: TerasRepository) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<PredictionData>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<PredictionData>>> get() = _uiState

    private val _nearbyCityState: MutableStateFlow<UiState<List<PredictionData>>> = MutableStateFlow(UiState.Loading)
    val nearbyCityState: StateFlow<UiState<List<PredictionData>>> get() = _nearbyCityState

    fun getSession(): Flow<UserModel> {
        return repository.getSession()
    }

    fun getTopThreePrediction(){
        viewModelScope.launch {
            val call : Call<PredictionResponse> = ApiConfig.api.getPrediction()
            call.enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<PredictionData>? = response.body()?.data?.take(4)

                        if(responseData != null){
                            _uiState.value = UiState.Success(responseData)
                        }
                    }

                }
                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    Log.d("Failed Retrieve Top Three Data", t.message.toString())
                }
            })
        }
    }

    fun getNearbyCities(longitude : Double, latitude : Double) {
        viewModelScope.launch {
            val call: Call<PredictionResponse> = ApiConfig.api.getPrediction()
            call.enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseData: List<PredictionData>? = response.body()?.data

                        if (responseData != null) {
                            val nearbyCities = findNearestCities(longitude, latitude, responseData)
                            _nearbyCityState.value = UiState.Success(nearbyCities)
                        }
                    }
                }

                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    Log.d("Failed Retrieve Nearby Data", t.message.toString())
                }
            })
        }
    }

    private fun findNearestCities(longitude: Double, latitude: Double, cities: List<PredictionData>): List<PredictionData> {
        val currentLatLng = LatLng(latitude, longitude)
        val nearbyCities = cities
            .filter { city ->
                city.prediction!! > 0
            }
            .sortedBy { city ->
                val cityLatLng = LatLng(city.latitude!!.toDouble(), city.longitude!!.toDouble())
                calculateDistance(currentLatLng, cityLatLng)
            }
            .take(3)
        return nearbyCities
    }

}