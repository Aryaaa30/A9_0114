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
            entity = Studio::class,
            parentColumns = ["id_studio"],
            childColumns = ["id_studio"],
            onDelete = ForeignKey.CASCADE // Data penayangan akan dihapus jika film terkait dihapus
        )
    ]
)
@Serializable
data class Studio(
    @PrimaryKey
    @SerialName("id_studio")
    val idStudio : String,

    @SerialName("nama_studio")
    val namaStudio : String,

    val kapasitas : String,
)
