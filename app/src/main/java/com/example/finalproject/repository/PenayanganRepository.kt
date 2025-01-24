package com.example.finalproject.repository

import com.example.finalproject.model.AllPenayanganResponse
import com.example.finalproject.model.Penayangan
import com.example.finalproject.service.PenayanganService
import java.io.IOException

interface PenayanganRepository {
    suspend fun getPenayangan(): AllPenayanganResponse
    suspend fun insertPenayangan(penayangan: Penayangan)
    suspend fun updatePenayangan(idPenayangan: String, penayangan: Penayangan)
    suspend fun deletePenayangan(idPenayangan: String)
    suspend fun getPenayanganById(idPenayangan: String): Penayangan
}

class NetworkPenayanganRepository(
    private val PenayanganApiService: PenayanganService
) : PenayanganRepository {


    override suspend fun insertPenayangan(penayangan: Penayangan) {
        PenayanganApiService.insertPenayangan(penayangan)
    }

    override suspend fun updatePenayangan(idPenayangan: String, penayangan: Penayangan) {
        PenayanganApiService.updatePenayangan(idPenayangan, penayangan)
    }

    override suspend fun deletePenayangan(idPenayangan: String) {
        try {
            val response = PenayanganApiService.deletePenayangan(idPenayangan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Film. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getPenayangan(): AllPenayanganResponse =
        PenayanganApiService.getAllPenayangan()

    override suspend fun getPenayanganById(idPenayangan: String): Penayangan {
        return PenayanganApiService.getPenayanganById(idPenayangan).data
    }

}