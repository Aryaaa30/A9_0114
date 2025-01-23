package com.example.finalproject.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.PenayanganRepository
import com.example.finalproject.ui.view.penayangan.DestinasiUpdatePenayangan
import com.example.finalproject.ui.viewmodel.studio.toUiStateMhs
import kotlinx.coroutines.launch

class UpdateViewModelPenayangan (
    savedStateHandle: SavedStateHandle,
    private val penayangan: PenayanganRepository
): ViewModel(){
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _idPenayangan: String = checkNotNull(savedStateHandle[DestinasiUpdatePenayangan.Penayangan])

    init {
        viewModelScope.launch {
            UpdateUiState = penayangan.getPenayanganById(_idPenayangan)
                .toUiStateMhs()
        }
    }


    fun updateInsertPenayanganState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updatePenayangan(){
        viewModelScope.launch {
            try {
                penayangan.updatePenayangan(_idPenayangan, UpdateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}