package com.example.finalproject.ui.viewmodel.studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Studio
import com.example.finalproject.repository.StudioRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState{
    data class Success(val studio: List<Studio>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}

class HomeViewModelStudio (private val studio: StudioRepository): ViewModel() {
    var studioUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getStudio()
    }

    fun getStudio(){
        viewModelScope.launch {
            studioUIState=HomeUiState.Loading
            studioUIState=try {
                HomeUiState.Success(studio.getStudio().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deleteStudio(idStudio:String) {
        viewModelScope.launch {
            try {
                studio.deleteStudio(idStudio)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}