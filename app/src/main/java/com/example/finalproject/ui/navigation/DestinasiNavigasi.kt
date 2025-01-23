package com.example.finalproject.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}





object DestinasiDetailPenayangan: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val Penayangan = "idPenayangan"
    val routesWithArg = "$route/{${Penayangan}}"
}

object DestinasiUpdatePenayangan: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Edit Mahasiswa"
    const val Penayangan = "idPenayangan"
    val routesWithArg = "$route/{$Penayangan}"
}