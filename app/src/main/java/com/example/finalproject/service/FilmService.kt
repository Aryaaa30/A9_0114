package com.example.finalproject.service

import com.example.finalproject.model.AllFilmResponse
import com.example.finalproject.model.Film
import com.example.finalproject.model.FilmDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("film")
    suspend fun getAllFilm(): AllFilmResponse

    @GET("film/{id}")
    suspend fun getFilmById(@Path("idFilm")idFilm: String):FilmDetailResponse

    @POST("film")
    suspend fun insertFilm(@Body film: Film)

    @PUT("film/{id}")
    suspend fun updateFilm(@Path("idFilm")idFilm: String, @Body film: Film)

    @DELETE("film/{id}")
    suspend fun deleteFilm(@Path("idFilm")idFilm: String):retrofit2.Response<Void>

}