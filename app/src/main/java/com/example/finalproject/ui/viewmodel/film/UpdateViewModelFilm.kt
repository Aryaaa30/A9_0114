package com.example.finalproject.ui.viewmodel.film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.FilmRepository
import com.example.finalproject.ui.view.film.DestinasiUpdateFilm
import kotlinx.coroutines.launch

class UpdateViewModelFilm (
    savedStateHandle: SavedStateHandle,
    private val film: FilmRepository
): ViewModel(){
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _idFilm: String = checkNotNull(savedStateHandle[DestinasiUpdateFilm.FILM])

    init {
        viewModelScope.launch {
            UpdateUiState = film.getFilmById(_idFilm)
                .toUiStateMhs()
        }
    }


    fun updateInsertFilmState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateFilm(){
        viewModelScope.launch {
            try {
                film.updateFilm(_idFilm, UpdateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}