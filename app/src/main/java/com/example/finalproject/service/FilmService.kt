package com.example.finalproject.service

import com.example.finalproject.model.Film
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FilmService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacafilm.php")
    suspend fun getAllFilm(): List<Film>

    @GET("baca1film.php")
    suspend fun getFilmById(@Query("idFilm")idFilm: String):Film

    @POST("insertfilm.php")
    suspend fun insertFilm(@Body film: Film)

    @PUT("editfilm.php/{idFilm}")
    suspend fun updateFilm(@Query("idFilm")idFilm: String, @Body film: Film)

    @DELETE("deletefilm.php/{idFilm}")
    suspend fun deleteFilm(@Query("idFilm")idFilm: String):retrofit2.Response<Void>


}