package com.example.finalproject.ui.viewmodel.studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.StudioRepository
import com.example.finalproject.ui.view.studio.DestinasiUpdateStudio
import kotlinx.coroutines.launch

class UpdateViewModelStudio (
    savedStateHandle: SavedStateHandle,
    private val studio: StudioRepository
): ViewModel(){
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _idStudio: String = checkNotNull(savedStateHandle[DestinasiUpdateStudio.Studio])

    init {
        viewModelScope.launch {
            UpdateUiState = studio.getStudioById(_idStudio)
                .toUiStateMhs()
        }
    }

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateStudio(){
        viewModelScope.launch {
            try {
                studio.updateStudio(_idStudio, UpdateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}