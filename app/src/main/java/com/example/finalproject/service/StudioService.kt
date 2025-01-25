package com.example.finalproject.service

import com.example.finalproject.model.Studio
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudioService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("studio")
    suspend fun getAllStudio(): List<Studio>

    @GET("studio/{id}")
    suspend fun getStudioById(@Path("id")idStudio: String): Studio

    @POST("studio")
    suspend fun insertStudio(@Body studio: Studio)

    @PUT("studio/{id}")
    suspend fun updateStudio(@Path("id")idStudio: String, @Body studio: Studio)

    @DELETE("studio/{id}")
    suspend fun deleteStudio(@Path("id")idStudio: String):retrofit2.Response<Void>
}