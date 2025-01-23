package com.example.finalproject.repository

import com.example.finalproject.model.Studio
import com.example.finalproject.service.StudioService
import java.io.IOException

interface StudioRepository {
    suspend fun getStudio(): List<Studio>
    suspend fun insertStudio(studio: Studio)
    suspend fun updateStudio(idStudio: String, studio: Studio)
    suspend fun deleteStudio(idStudio: String)
    suspend fun getStudioById(idStudio: String): Studio
}

class NetworkStudioRepository(
    private val studioApiService: StudioService
) : StudioRepository {


    override suspend fun insertStudio(studio: Studio) {
        studioApiService.insertStudio(studio)
    }

    override suspend fun updateStudio(idStudio: String, studio: Studio) {
        studioApiService.updateStudio(idStudio, studio)
    }

    override suspend fun deleteStudio(idStudio: String) {
        try {
            val response = studioApiService.deleteStudio(idStudio)
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

    override suspend fun getStudio(): List<Studio> =
        studioApiService.getAllStudio()

    override suspend fun getStudioById(idStudio: String): Studio {
        return studioApiService.getStudioById(idStudio)
    }
}