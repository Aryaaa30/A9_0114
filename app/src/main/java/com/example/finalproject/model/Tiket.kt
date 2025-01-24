package com.example.finalproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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