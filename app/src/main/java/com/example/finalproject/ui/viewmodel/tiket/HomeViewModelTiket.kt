package com.example.finalproject.ui.viewmodel.tiket

import com.example.finalproject.model.Tiket

sealed class HomeUiState{
    data class Success(val tiket: List<Tiket>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}