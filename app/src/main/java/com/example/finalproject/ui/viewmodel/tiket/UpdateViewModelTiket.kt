package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.ui.navigation.DestinasiUpdateTiket
import kotlinx.coroutines.launch

class UpdateViewModelTiket (
    savedStateHandle: SavedStateHandle,
    private val tiket: TiketRepository
): ViewModel(){
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _idTiket: String = checkNotNull(savedStateHandle[DestinasiUpdateTiket.Tiket])

    init {
        viewModelScope.launch {
            UpdateUiState = tiket.getTiketById(_idTiket)
                .toUiStateMhs()
        }
    }

    fun updateInsertTiketState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateTiket(){
        viewModelScope.launch {
            try {
                tiket.updateTiket(_idTiket, UpdateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}