package com.example.finalproject.service

import com.example.finalproject.model.Film
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FilmService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("film")
    suspend fun getAllFilm(): List<Film>

    @GET("film/{id}")
    suspend fun getFilmById(@Path("id")idFilm: String):Film

    @POST("film")
    suspend fun insertFilm(@Body film: Film)

    @PUT("film/{id}")
    suspend fun updateFilm(@Path("id")idFilm: String, @Body film: Film)

    @DELETE("film/{id}")
    suspend fun deleteFilm(@Path("id")idFilm: String):retrofit2.Response<Void>

}