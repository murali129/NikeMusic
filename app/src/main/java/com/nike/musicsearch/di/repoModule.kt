package com.nike.musicsearch.di

import com.nike.musicsearch.repo.SongsRepo
import org.koin.dsl.module

val repositoryModule = module {
    factory { SongsRepo(get(), get()) }
}