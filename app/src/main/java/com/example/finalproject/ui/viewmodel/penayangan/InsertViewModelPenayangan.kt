package com.example.finalproject.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Film
import com.example.finalproject.model.Penayangan
import com.example.finalproject.model.Studio
import com.example.finalproject.repository.PenayanganRepository
import com.example.finalproject.service.FilmService
import com.example.finalproject.service.PenayanganService
import com.example.finalproject.service.StudioService
import kotlinx.coroutines.launch

class InsertViewModelPenayangan(
    private val penayanganService: PenayanganService,
    private val filmService: FilmService,
    private val studioService: StudioService,
    private val penayangan: PenayanganRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    var allFilms by mutableStateOf<List<Film>>(emptyList())
        private set

    init {
        loadAvailableFilms()
    }

    private fun loadAvailableFilms() {
        viewModelScope.launch {
            try {
                // Ambil semua film dari FilmService
                allFilms = filmService.getAllFilm()

                // Ambil semua ID Film yang sudah digunakan di Penayangan
                val usedFilmIds = penayangan.getPenayangan().map { it.idFilm }

                // Cari ID Film yang belum digunakan
                val availableFilm = allFilms.find { it.idFilm !in usedFilmIds }

                // Setel ID Film pertama yang belum digunakan (jika ada)
                availableFilm?.let {
                    val updatedUiEvent = uiState.insertUiEvent.copy(idFilm = it.idFilm)
                    uiState = uiState.copy(insertUiEvent = updatedUiEvent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var allStudios by mutableStateOf<List<Studio>>(emptyList())
        private set

    init {
        loadAvailableStudios()
    }

    private fun loadAvailableStudios() {
        viewModelScope.launch {
            try {
                // Ambil semua film dari FilmService
                allStudios = studioService.getAllStudio()

                // Ambil semua ID Film yang sudah digunakan di Penayangan
                val usedStudioIds = penayangan.getPenayangan().map { it.idStudio }

                // Cari ID Film yang belum digunakan
                val availableStudio = allStudios.find { it.idStudio !in usedStudioIds }

                // Setel ID Film pertama yang belum digunakan (jika ada)
                availableStudio?.let {
                    val updatedUiEvent = uiState.insertUiEvent.copy(idStudio = it.idStudio)
                    uiState = uiState.copy(insertUiEvent = updatedUiEvent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertPenayanganState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPenayangan() {
        viewModelScope.launch {
            try {
                penayanganService.insertPenayangan(uiState.insertUiEvent.toMhs())
            } catch (e: Exception) {
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