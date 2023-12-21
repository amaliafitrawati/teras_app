package com.bangkit.teras_app.ui.screen.story

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.TerasRepository
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.response.ForumData
import com.bangkit.teras_app.data.response.ForumResponse
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryViewModel(private val repository: TerasRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ForumData>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ForumData>>> get() = _uiState

     fun getAllStory(){
        viewModelScope.launch {
            val call : Call<ForumResponse> = ApiConfig.api.getForum()
            call.enqueue(object : Callback<ForumResponse> {
                override fun onResponse(
                    call: Call<ForumResponse>,
                    response: Response<ForumResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<ForumData>? = response.body()?.data
                        if(responseData != null){
                            _uiState.value = UiState.Success(responseData)
                        }
                    }
                }
                override fun onFailure(call: Call<ForumResponse>, t: Throwable) {
                    Log.d("Failed Retrieve Forum Data", t.message.toString())
                }
            })
        }
    }

}