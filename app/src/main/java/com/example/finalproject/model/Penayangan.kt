package com.example.finalproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Penayangan(
    @SerialName("id_penayangan")
    val idPenayangan : String,

    @SerialName("id_film")
    val idFilm : String,

    @SerialName("id_studio")
    val idStudio : String,

    @SerialName("tanggal_penayangan")
    val tanggalPenayangan : String,

    @SerialName("harga_tiket")
    val hargaTiket : String,
)

@Serializable
data class AllPenayanganResponse(
    val status: Boolean,
    val message: String,
    val data: List<Penayangan>
)
@Serializable
data class PenayanganDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Penayangan
)