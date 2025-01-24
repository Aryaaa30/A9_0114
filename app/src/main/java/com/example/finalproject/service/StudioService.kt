package com.example.finalproject.service

import com.example.finalproject.model.AllFilmResponse
import com.example.finalproject.model.AllStudioResponse
import com.example.finalproject.model.Film
import com.example.finalproject.model.FilmDetailResponse
import com.example.finalproject.model.Studio
import com.example.finalproject.model.StudioDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface StudioService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("studio")
    suspend fun getAllStudio(): AllStudioResponse

    @GET("studio/{id}")
    suspend fun getStudioById(@Path("idStudio")idStudio: String): StudioDetailResponse

    @POST("studio")
    suspend fun insertStudio(@Body studio: Studio)

    @PUT("studio/{id}")
    suspend fun updateStudio(@Path("idStudio")idStudio: String, @Body studio: Studio)

    @DELETE("studio/{id}")
    suspend fun deleteStudio(@Path("idStudio")idStudio: String):retrofit2.Response<Void>
}