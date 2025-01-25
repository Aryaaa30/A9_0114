package com.example.finalproject.service

import com.example.finalproject.model.Tiket
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TiketService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("tiket")
    suspend fun getAllTiket(): List<Tiket>

    @GET("tiket/{id}")
    suspend fun getTiketById(@Path("id")idTiket: String): Tiket

    @POST("tiket")
    suspend fun insertTiket(@Body tiket: Tiket)

    @PUT("tiket/{id}")
    suspend fun updateTiket(@Path("id")idTiket: String, @Body tiket: Tiket)

    @DELETE("tiket/{id}")
    suspend fun deleteTiket(@Path("id")idTiket: String):retrofit2.Response<Void>
}