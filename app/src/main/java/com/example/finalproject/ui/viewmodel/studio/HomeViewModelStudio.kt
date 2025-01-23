package com.example.finalproject.ui.viewmodel.studio

import com.example.finalproject.model.Studio

sealed class HomeUiState{
    data class Success(val studio: List<Studio>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}