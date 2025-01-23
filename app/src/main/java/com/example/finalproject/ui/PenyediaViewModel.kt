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
import com.example.finalproject.ui.viewmodel.penayangan.DetailViewModelPenayangan
import com.example.finalproject.ui.viewmodel.penayangan.HomeViewModelPenayangan
import com.example.finalproject.ui.viewmodel.penayangan.InsertViewModelPenayangan
import com.example.finalproject.ui.viewmodel.penayangan.UpdateViewModelPenayangan
import com.example.finalproject.ui.viewmodel.studio.DetailViewModelStudio
import com.example.finalproject.ui.viewmodel.studio.HomeViewModelStudio
import com.example.finalproject.ui.viewmodel.studio.InsertViewModelStudio
import com.example.finalproject.ui.viewmodel.studio.UpdateViewModelStudio
import com.example.finalproject.ui.viewmodel.tiket.DetailViewModelTiket
import com.example.finalproject.ui.viewmodel.tiket.HomeViewModelTiket
import com.example.finalproject.ui.viewmodel.tiket.InsertViewModelTiket
import com.example.finalproject.ui.viewmodel.tiket.UpdateViewModelTiket

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeViewModelFilm(aplikasiFilm().container.filmRepository) }
        initializer { InsertViewModelFilm(aplikasiFilm().container.filmRepository) }
        initializer { DetailViewModelFilm(createSavedStateHandle(),aplikasiFilm().container.filmRepository) }
        initializer { UpdateViewModelFilm(createSavedStateHandle(),aplikasiFilm().container.filmRepository) }
        initializer { HomeViewModelPenayangan(aplikasiFilm().container.penayanganRepository) }
        initializer { InsertViewModelPenayangan(aplikasiFilm().container.penayanganRepository) }
        initializer { DetailViewModelPenayangan(createSavedStateHandle(),aplikasiFilm().container.penayanganRepository) }
        initializer { UpdateViewModelPenayangan(createSavedStateHandle(),aplikasiFilm().container.penayanganRepository) }
        initializer { HomeViewModelStudio(aplikasiFilm().container.studioRepository) }
        initializer { InsertViewModelStudio(aplikasiFilm().container.studioRepository) }
        initializer { DetailViewModelStudio(createSavedStateHandle(),aplikasiFilm().container.studioRepository) }
        initializer { UpdateViewModelStudio(createSavedStateHandle(),aplikasiFilm().container.studioRepository) }
        initializer { HomeViewModelTiket(aplikasiFilm().container.tiketRepository) }
        initializer { InsertViewModelTiket(aplikasiFilm().container.tiketRepository) }
        initializer { DetailViewModelTiket(createSavedStateHandle(),aplikasiFilm().container.tiketRepository) }
        initializer { UpdateViewModelTiket(createSavedStateHandle(),aplikasiFilm().container.tiketRepository) }
    }
    fun CreationExtras.aplikasiFilm(): CinemasApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as CinemasApplications)
}