package com.example.finalproject.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Penayangan
import com.example.finalproject.repository.PenayanganRepository
import kotlinx.coroutines.launch

class InsertViewModelPenayangan (private val penayangan: PenayanganRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPenayanganState(insertUiEvent:InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPenayangan() {
        viewModelScope.launch{
            try {
                penayangan.insertPenayangan(uiState.insertUiEvent.toMhs())
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
    val idPenayangan: String="",
    val idFilm: String="",
    val idStudio: String="",
    val tanggalPenayangan: String="",
    val hargaTiket: String="",
)

fun InsertUiEvent.toMhs(): Penayangan = Penayangan(
    idPenayangan = idPenayangan,
    idFilm = idFilm,
    idStudio = idStudio,
    tanggalPenayangan = tanggalPenayangan,
    hargaTiket = hargaTiket,
)

fun Penayangan.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Penayangan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idPenayangan = idPenayangan,
    idFilm = idFilm,
    idStudio = idStudio,
    tanggalPenayangan = tanggalPenayangan,
    hargaTiket = hargaTiket,
)