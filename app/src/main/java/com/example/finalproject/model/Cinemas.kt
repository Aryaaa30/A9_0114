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
data class Studio(
    @SerialName("id_studio")
    val idStudio : String,

    @SerialName("nama_studio")
    val namaStudio : String,

    val kapasitas : String,
)

@Serializable
data class Tiket(
    @SerialName("id_tiket")
    val idTiket : String,

    @SerialName("id_penayangan")
    val idPenayangan : String,

    @SerialName("jumlah_tiket")
    val jumlahTiket : String,

    @SerialName("total_harga")
    val totalHarga : String,

    @SerialName("status_pembayaran")
    val statusPembayaran : String,
)

