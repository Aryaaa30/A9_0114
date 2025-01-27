package com.example.finalproject.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = "penayangan",
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["id_film"],
            childColumns = ["id_film"],
            onDelete = ForeignKey.CASCADE // Data penayangan akan dihapus jika film terkait dihapus
        )
    ]
)
@Serializable
data class Penayangan(
    @PrimaryKey
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

