package com.nike.musicsearch.di

import com.nike.musicsearch.persitence.AppDataBase
import org.koin.dsl.module

val roomModule = module {
    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getTrackDao() }
}