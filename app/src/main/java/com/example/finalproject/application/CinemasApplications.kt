package com.example.finalproject.application

import android.app.Application
import com.example.finalproject.dependenciesinjection.AppContainer
import com.example.finalproject.dependenciesinjection.CinemasContainer

class CinemasApplications: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = CinemasContainer()
    }

}