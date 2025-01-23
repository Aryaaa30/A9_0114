package com.example.finalproject.ui.viewmodel.film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Film
import com.example.finalproject.repository.FilmRepository
import com.example.finalproject.ui.view.film.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val film: Film) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModelFilm(
    savedStateHandle: SavedStateHandle,
    private val film: FilmRepository
) : ViewModel() {

    var filmDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idFilm: String = checkNotNull(savedStateHandle[DestinasiDetail.FILM])

    init {
        getFilmById()
    }

    fun getFilmById() {
        viewModelScope.launch {
            filmDetailState = DetailUiState.Loading
            filmDetailState = try {
                val film = film.getFilmById(_idFilm)
                DetailUiState.Success(film)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteFilm(idFilm:String) {
        viewModelScope.launch {
            try {
                film.deleteFilm(idFilm)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}