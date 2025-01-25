package com.example.finalproject.service

import com.example.finalproject.model.Penayangan
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenayanganService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("penayangan")
    suspend fun getAllPenayangan(): List<Penayangan>

    @GET("penayangan/{id}")
    suspend fun getPenayanganById(@Path("id")idPenayangan: String): Penayangan

    @POST("penayangan")
    suspend fun insertPenayangan(@Body penayangan: Penayangan)

    @PUT("penayangan/{id}")
    suspend fun updatePenayangan(@Path("id")idPenayangan: String, @Body penayangan: Penayangan)

    @DELETE("penayangan/{id}")
    suspend fun deletePenayangan(@Path("id")idPenayangan: String):retrofit2.Response<Void>
}