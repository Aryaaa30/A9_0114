package com.example.finalproject.repository

import com.example.finalproject.model.Film
import com.example.finalproject.service.FilmService
import java.io.IOException

interface FilmRepository {
    suspend fun getFilm(): List<Film>
    suspend fun insertFilm(film: Film)
    suspend fun updateFilm(idFilm: String, film: Film)
    suspend fun deleteFilm(idFilm: String)
    suspend fun getFilmById(idFilm: String): Film
}

class NetworkFilmRepository(
    private val filmApiService: FilmService
) : FilmRepository {


    override suspend fun insertFilm(film: Film) {
        filmApiService.insertFilm(film)
    }

    override suspend fun updateFilm(idFilm: String, film: Film) {
        filmApiService.updateFilm(idFilm, film)
    }

    override suspend fun deleteFilm(idFilm: String) {
        try {
            val response = filmApiService.deleteFilm(idFilm)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Film. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getFilm(): List<Film> =
        filmApiService.getAllFilm()

    override suspend fun getFilmById(idFilm: String): Film {
        return filmApiService.getFilmById(idFilm)
    }

}