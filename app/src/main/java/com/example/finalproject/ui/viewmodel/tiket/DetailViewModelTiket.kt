package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.ui.navigation.DestinasiDetailTiket
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val tiket: Tiket) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModelTiket(
    savedStateHandle: SavedStateHandle,
    private val tiket: TiketRepository
) : ViewModel() {

    var tiketDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idTiket: String = checkNotNull(savedStateHandle[DestinasiDetailTiket.Tiket])

    init {
        getTiketById()
    }

    fun getTiketById() {
        viewModelScope.launch {
            tiketDetailState = DetailUiState.Loading
            tiketDetailState = try {
                val tiket = tiket.getTiketById(_idTiket)
                DetailUiState.Success(tiket)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteTiket(idTiket:String) {
        viewModelScope.launch {
            try {
                tiket.deleteTiket(idTiket)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}