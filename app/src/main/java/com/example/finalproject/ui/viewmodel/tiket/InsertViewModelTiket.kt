package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import kotlinx.coroutines.launch

class InsertViewModelTiket (private val tiket: TiketRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

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