package com.example.finalproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Studio(
    @SerialName("id_studio")
    val idStudio : String,

    @SerialName("nama_studio")
    val namaStudio : String,

    val kapasitas : String,
)