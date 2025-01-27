package com.example.finalproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = "film"
)
@Serializable
data class Film(
    @PrimaryKey
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



