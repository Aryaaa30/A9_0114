package com.example.finalproject.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.application.CinemasApplications

object PenyediaViewModel{
    val Factory = viewModelFactory {

    }
    fun CreationExtras.aplikasiMahasiswa(): CinemasApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as CinemasApplications)
}