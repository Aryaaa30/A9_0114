package com.example.finalproject.service

import com.example.finalproject.model.Film
import com.example.finalproject.model.Penayangan
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PenayanganService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacapenayangan.php")
    suspend fun getAllPenayangan(): List<Penayangan>

    @GET("baca1penayangan.php")
    suspend fun getPenayanganById(@Query("idPenayangan")idPenayangan: String): Penayangan

    @POST("insertpenayangan.php")
    suspend fun insertPenayangan(@Body penayangan: Penayangan)

    @PUT("editpenayangan.php/{idPenayangan}")
    suspend fun updatePenayangan(@Query("idPenayangan")idPenayangan: String, @Body penayangan: Penayangan)

    @DELETE("deletepenayangan.php/{idPenayangan}")
    suspend fun deletePenayangan(@Query("idPenayangan")idPenayangan: String):retrofit2.Response<Void>
}