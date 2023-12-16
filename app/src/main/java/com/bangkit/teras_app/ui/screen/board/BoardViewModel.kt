package com.bangkit.teras_app.ui.screen.board

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.model.RiceProduction
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BoardViewModel(private val repository: RiceProductionRepository) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<RiceProduction>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<RiceProduction>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun filterByYear(year : String) = viewModelScope.launch {
        _query.value = year
        val year2 = null
        repository.getRiceByYear(year2)
            .catch{
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiState.value = UiState.Success(it)
            }
    }

}