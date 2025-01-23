package com.example.finalproject.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}







object DestinasiUpdatePenayangan: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Edit Mahasiswa"
    const val Penayangan = "idPenayangan"
    val routesWithArg = "$route/{$Penayangan}"
}