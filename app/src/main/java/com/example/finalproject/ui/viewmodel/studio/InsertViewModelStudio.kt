package com.example.finalproject.ui.viewmodel.studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Studio
import com.example.finalproject.repository.StudioRepository
import kotlinx.coroutines.launch

class InsertViewModelStudio (private val studio: StudioRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertStudioState(insertUiEvent:InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertStudio() {
        viewModelScope.launch{
            try {
                studio.insertStudio(uiState.insertUiEvent.toMhs())
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
    val idStudio: String="",
    val namaStudio: String="",
    val kapasitas: String="",
)

fun InsertUiEvent.toMhs(): Studio = Studio(
    idStudio = idStudio,
    namaStudio = namaStudio,
    kapasitas = kapasitas,
)

fun Studio.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Studio.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idStudio = idStudio,
    namaStudio = namaStudio,
    kapasitas = kapasitas,
)