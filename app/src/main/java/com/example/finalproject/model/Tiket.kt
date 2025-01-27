package com.example.finalproject.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = "tiket",
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["id_penayangan"],
            childColumns = ["id_penayangan"],
            onDelete = ForeignKey.CASCADE // Data penayangan akan dihapus jika film terkait dihapus
        )
    ]
)
@Serializable
data class Tiket(
    @PrimaryKey
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
