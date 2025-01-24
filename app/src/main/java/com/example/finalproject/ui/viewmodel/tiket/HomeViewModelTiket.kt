package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState{
    data class Success(val tiket: List<Tiket>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}

class HomeViewModelTiket (private val tiket: TiketRepository): ViewModel() {
    var tiketUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getTiket()
    }

    fun getTiket(){
        viewModelScope.launch {
            tiketUIState=HomeUiState.Loading
            tiketUIState=try {
                HomeUiState.Success(tiket.getTiket().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
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