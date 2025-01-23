package com.example.finalproject.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val FILM = "idFilm"
    val routesWithArg = "$route/{$FILM}"
}