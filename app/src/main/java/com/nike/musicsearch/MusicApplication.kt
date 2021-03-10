package com.nike.musicsearch

import android.app.Application
import com.nike.musicsearch.di.networkModule
import com.nike.musicsearch.di.repositoryModule
import com.nike.musicsearch.di.roomModule
import com.nike.musicsearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MusicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@MusicApplication)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule))
        }
    }
}