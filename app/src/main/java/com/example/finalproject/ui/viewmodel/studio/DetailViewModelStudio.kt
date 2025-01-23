package com.example.finalproject.ui.viewmodel.studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Studio
import com.example.finalproject.repository.StudioRepository
import com.example.finalproject.ui.navigation.DestinasiDetailStudio
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val studio: Studio) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModelStudio(
    savedStateHandle: SavedStateHandle,
    private val studio: StudioRepository
) : ViewModel() {

    var studioDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idStudio: String = checkNotNull(savedStateHandle[DestinasiDetailStudio.Studio])

    init {
        getStudioById()
    }

    fun getStudioById() {
        viewModelScope.launch {
            studioDetailState = DetailUiState.Loading
            studioDetailState = try {
                val mahasiswa = studio.getStudioById(_idStudio)
                DetailUiState.Success(mahasiswa)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
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