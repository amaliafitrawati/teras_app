package com.bangkit.teras_app.ui.screen.board

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.response.PredictionData
import com.bangkit.teras_app.data.response.PredictionResponse
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardViewModel(private val repository: RiceProductionRepository) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<PredictionData>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<PredictionData>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

//    fun filterByYear(year : String) = viewModelScope.launch {
//        _query.value = year
//        val year2 = null
//        repository.getRiceByYear(year2)
//            .catch{
//                _uiState.value = UiState.Error(it.message.toString())
//            }
//            .collect{
//                _uiState.value = UiState.Success(it)
//            }
//    }

    fun getAllPrediction(){
        viewModelScope.launch {
            val call : Call<PredictionResponse> = ApiConfig.api.getPrediction()
            call.enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>
                ) {
                    Log.e("Check Response Data", response.toString())
                    Log.e("Check Success Resp Data", response.isSuccessful.toString())
                    if(response.isSuccessful){
                        Log.e("Check View Model Data", response.body()?.data.toString())
                        val responseData: List<PredictionData>? = response.body()?.data
                        if(responseData != null){
                            Log.e("SUCCESS DATA", responseData.toString())
                            _uiState.value = UiState.Success(responseData)
                        }
                    }
                }
                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    Log.d("Failed Retrieve Leader", t.message.toString())
                }
            })
        }
    }

}