package com.example.finalproject.service

import com.example.finalproject.model.Studio
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface StudioService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacastudio.php")
    suspend fun getAllStudio(): List<Studio>

    @GET("baca1studio.php")
    suspend fun getStudioById(@Query("idStudio")idStudio: String): Studio

    @POST("insertstudio.php")
    suspend fun insertStudio(@Body studio: Studio)

    @PUT("editstudio.php/{idStudio}")
    suspend fun updateStudio(@Query("idStudio")idStudio: String, @Body studio: Studio)

    @DELETE("deletestudio.php/{idStudio}")
    suspend fun deleteStudio(@Query("idStudio")idStudio: String):retrofit2.Response<Void>
}