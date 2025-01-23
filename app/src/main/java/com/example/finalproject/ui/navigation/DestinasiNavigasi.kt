package com.example.finalproject.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiDetailStudio: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val Studio = "idStudio"
    val routesWithArg = "$route/{$Studio}"
}
object DestinasiUpdateStudio: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Edit Studio"
    const val Studio = "idStudio"
    val routesWithArg = "$route/{$Studio}"
}

object DestinasiDetailTiket: DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Tiket"
    const val Tiket = "idTiket"
    val routesWithArg = "$route/{$Tiket}"
}

object DestinasiUpdateTiket: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Edit Tiket"
    const val Tiket = "idTiket"
    val routesWithArg = "$route/{$Tiket}"
}




