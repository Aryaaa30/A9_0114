package com.example.finalproject.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Penayangan
import com.example.finalproject.repository.PenayanganRepository
import com.example.finalproject.ui.navigation.DestinasiDetailPenayangan
import com.example.finalproject.ui.viewmodel.film.HomeUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val penayangan: Penayangan) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModelPenayangan(
    savedStateHandle: SavedStateHandle,
    private val penayangan: PenayanganRepository
) : ViewModel() {

    var penayanganDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idPenayangan: String = checkNotNull(savedStateHandle[DestinasiDetailPenayangan.Penayangan])

    init {
        getPenayanganById()
    }

    fun getPenayanganById() {
        viewModelScope.launch {
            penayanganDetailState = DetailUiState.Loading
            penayanganDetailState = try {
                val penayangan = penayangan.getPenayanganById(_idPenayangan)
                DetailUiState.Success(penayangan)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deletePenayangan(idPenayangan:String) {
        viewModelScope.launch {
            try {
                penayangan.deletePenayangan(idPenayangan)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}