package com.example.finalproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerialName("id_film")
    val idFilm : String,

    @SerialName("judul_film")
    val judulFilm : String,

    val durasi : String,
    val deskripsi : String,
    val genre : String,

    @SerialName("rating_usia")
    val ratingUsia : String,
)

@Serializable
data class AllFilmResponse(
    val status: Boolean,
    val message: String,
    val data: List<Film>
)
@Serializable
data class FilmDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Film
)
