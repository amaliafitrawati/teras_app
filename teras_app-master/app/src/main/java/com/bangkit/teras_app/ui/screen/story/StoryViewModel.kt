package com.bangkit.teras_app.ui.screen.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.model.Forum
import com.bangkit.teras_app.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: RiceProductionRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Forum>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Forum>>> get() = _uiState

    fun getAllStory() = viewModelScope.launch {
        repository.getAllForumData()
            .catch{
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{
                _uiState.value = UiState.Success(it)
            }
    }

}