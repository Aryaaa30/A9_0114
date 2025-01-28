package com.example.finalproject.ui.viewmodel.film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Film
import com.example.finalproject.repository.FilmRepository
import kotlinx.coroutines.launch

class InsertViewModelFilm (private val film: FilmRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertFilmState(insertUiEvent:InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertFilm() {
        viewModelScope.launch{
            try {
                film.insertFilm(uiState.insertUiEvent.toMhs())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idFilm: String="",
    val judulFilm: String="",
    val durasi: String="",
    val deskripsi: String="",
    val genre: String="",
    val ratingUsia: String="",
)

fun InsertUiEvent.toMhs(): Film = Film(
    idFilm = idFilm,
    judulFilm = judulFilm,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    ratingUsia = ratingUsia,
)

fun Film.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Film.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idFilm = idFilm,
    judulFilm = judulFilm,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    ratingUsia = ratingUsia,
)