package com.example.finalproject.ui.viewmodel.film

import com.example.finalproject.model.Film

sealed class HomeUiState{
    data class Success(val film: List<Film>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}