package com.example.finalproject.service

import com.example.finalproject.model.AllFilmResponse
import com.example.finalproject.model.AllPenayanganResponse
import com.example.finalproject.model.Film
import com.example.finalproject.model.FilmDetailResponse
import com.example.finalproject.model.Penayangan
import com.example.finalproject.model.PenayanganDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PenayanganService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("penayangan")
    suspend fun getAllPenayangan(): AllPenayanganResponse

    @GET("penayangan/{id}")
    suspend fun getPenayanganById(@Path("idPenayangan")idPenayangan: String): PenayanganDetailResponse

    @POST("penayangan")
    suspend fun insertPenayangan(@Body penayangan: Penayangan)

    @PUT("penayangan/{id}")
    suspend fun updatePenayangan(@Path("idPenayangan")idPenayangan: String, @Body penayangan: Penayangan)

    @DELETE("penayangan/{id}")
    suspend fun deletePenayangan(@Path("idPenayangan")idPenayangan: String):retrofit2.Response<Void>
}