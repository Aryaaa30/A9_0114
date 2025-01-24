package com.example.finalproject.service

import com.example.finalproject.model.AllFilmResponse
import com.example.finalproject.model.AllTiketResponse
import com.example.finalproject.model.Film
import com.example.finalproject.model.FilmDetailResponse
import com.example.finalproject.model.Tiket
import com.example.finalproject.model.TiketDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TiketService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("tiket")
    suspend fun getAllTiket(): AllTiketResponse

    @GET("tiket/{id}")
    suspend fun getTiketById(@Path("idTiket")idTiket: String): TiketDetailResponse

    @POST("tiket")
    suspend fun insertTiket(@Body tiket: Tiket)

    @PUT("tiket/{id}")
    suspend fun updateTiket(@Path("idTiket")idTiket: String, @Body tiket: Tiket)

    @DELETE("tiket/{id}")
    suspend fun deleteTiket(@Path("idTiket")idTiket: String):retrofit2.Response<Void>
}