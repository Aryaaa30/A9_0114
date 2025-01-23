package com.example.finalproject.dependenciesinjection

import com.example.finalproject.repository.FilmRepository
import com.example.finalproject.repository.NetworkFilmRepository
import com.example.finalproject.repository.NetworkPenayanganRepository
import com.example.finalproject.repository.NetworkStudioRepository
import com.example.finalproject.repository.NetworkTiketRepository
import com.example.finalproject.repository.PenayanganRepository
import com.example.finalproject.repository.StudioRepository
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.service.FilmService
import com.example.finalproject.service.PenayanganService
import com.example.finalproject.service.StudioService
import com.example.finalproject.service.TiketService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val filmRepository: FilmRepository
    val penayanganRepository: PenayanganRepository
    val studioRepository: StudioRepository
    val tiketRepository: TiketRepository
}

class CinemasContainer : AppContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/mahasiswa/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val filmService: FilmService by lazy {
        retrofit.create(FilmService::class.java)
    }
    override val filmRepository: FilmRepository by lazy {
        NetworkFilmRepository(filmService)
    }
    private val penayanganService: PenayanganService by lazy {
        retrofit.create(PenayanganService::class.java)
    }
    override val penayanganRepository: PenayanganRepository by lazy {
        NetworkPenayanganRepository(penayanganService)
    }
    private val studioService: StudioService by lazy {
        retrofit.create(StudioService::class.java)
    }
    override val studioRepository: StudioRepository by lazy {
        NetworkStudioRepository(studioService)
    }
    private val tiketService: TiketService by lazy {
        retrofit.create(TiketService::class.java)
    }
    override val tiketRepository: TiketRepository by lazy {
        NetworkTiketRepository(tiketService)
    }
}