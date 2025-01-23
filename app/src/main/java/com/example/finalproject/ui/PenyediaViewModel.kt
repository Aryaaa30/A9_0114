package com.example.finalproject.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.application.CinemasApplications
import com.example.finalproject.ui.viewmodel.film.DetailViewModelFilm
import com.example.finalproject.ui.viewmodel.film.HomeViewModelFilm
import com.example.finalproject.ui.viewmodel.film.InsertViewModelFilm
import com.example.finalproject.ui.viewmodel.film.UpdateViewModelFilm

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeViewModelFilm(aplikasiFilm().container.filmRepository) }
        initializer { InsertViewModelFilm(aplikasiFilm().container.filmRepository) }
        initializer { DetailViewModelFilm(createSavedStateHandle(),aplikasiFilm().container.filmRepository) }
        initializer { UpdateViewModelFilm(createSavedStateHandle(),aplikasiFilm().container.filmRepository) }
    }
    fun CreationExtras.aplikasiFilm(): CinemasApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as CinemasApplications)
}