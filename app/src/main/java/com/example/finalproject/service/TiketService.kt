package com.example.finalproject.service

import com.example.finalproject.model.Tiket
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TiketService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacatiket.php")
    suspend fun getAllTiket(): List<Tiket>

    @GET("baca1tiket.php")
    suspend fun getTiketById(@Query("idTiket")idTiket: String): Tiket

    @POST("inserttiket.php")
    suspend fun insertTiket(@Body tiket: Tiket)

    @PUT("edittiket.php/{idTiket}")
    suspend fun updateTiket(@Query("idTiket")idTiket: String, @Body tiket: Tiket)

    @DELETE("deletetiket.php/{idTiket}")
    suspend fun deleteTiket(@Query("idTiket")idTiket: String):retrofit2.Response<Void>
}