package com.example.finalproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id_film : String,
    val judul_film : String,
    val durasi : String,
    val deskripsi : String,
    val genre : String,
    val rating_usia : String,
)
