package com.example.finalproject.ui.viewmodel.film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Film
import com.example.finalproject.repository.FilmRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState{
    data class Success(val film: List<Film>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}

class HomeViewModelFilm (private val film: FilmRepository): ViewModel() {
    var filmUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getFilm()
    }

    fun getFilm(){
        viewModelScope.launch {
            filmUIState=HomeUiState.Loading
            filmUIState=try {
                HomeUiState.Success(film.getFilm().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
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