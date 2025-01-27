package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Film
import com.example.finalproject.model.Penayangan
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.service.PenayanganService
import kotlinx.coroutines.launch

class InsertViewModelTiket (
    private val tiket: TiketRepository,
    private val penayanganService: PenayanganService
): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    var allPenayangan by mutableStateOf<List<Penayangan>>(emptyList())
        private set

    init {
        loadAvailablePenayangan()
    }

    private fun loadAvailablePenayangan() {
        viewModelScope.launch {
            try {
                // Ambil semua film dari FilmService
                allPenayangan = penayanganService.getAllPenayangan()

                // Ambil semua ID Film yang sudah digunakan di Penayangan
                val usedenayanganIds = tiket.getTiket().map { it.idPenayangan }

                // Cari ID Film yang belum digunakan
                val availablePenayangan = allPenayangan.find { it.idPenayangan !in usedenayanganIds }

                // Setel ID Film pertama yang belum digunakan (jika ada)
                availablePenayangan?.let {
                    val updatedUiEvent = uiState.insertUiEvent.copy(idPenayangan = it.idPenayangan)
                    uiState = uiState.copy(insertUiEvent = updatedUiEvent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInsertTiketState(insertUiEvent:InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertTiket() {
        viewModelScope.launch{
            try {
                tiket.insertTiket(uiState.insertUiEvent.toMhs())
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
    val idTiket: String="",
    val idPenayangan: String="",
    val jumlahTiket: String="",
    val totalHarga: String="",
    val statusPembayaran: String="",
)

fun InsertUiEvent.toMhs(): Tiket = Tiket(
    idTiket = idTiket,
    idPenayangan = idPenayangan,
    jumlahTiket = jumlahTiket,
    totalHarga = totalHarga,
    statusPembayaran = statusPembayaran,
)

fun Tiket.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Tiket.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idTiket = idTiket,
    idPenayangan = idPenayangan,
    jumlahTiket = jumlahTiket,
    totalHarga = totalHarga,
    statusPembayaran = statusPembayaran,
)