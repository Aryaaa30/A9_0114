package com.example.finalproject.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Penayangan
import com.example.finalproject.repository.PenayanganRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState{
    data class Success(val penayangan: List<Penayangan>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}

class HomeViewModelPenayangan (private val penayangan: PenayanganRepository): ViewModel() {
    var penayanganUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPenayangan()
    }

    fun getPenayangan(){
        viewModelScope.launch {
            penayanganUIState=HomeUiState.Loading
            penayanganUIState=try {
                HomeUiState.Success(penayangan.getPenayangan().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
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