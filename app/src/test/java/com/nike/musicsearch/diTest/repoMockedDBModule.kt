package com.nike.musicsearch.diTest

import com.nike.musicsearch.persitence.TrackDao
import com.nike.musicsearch.repo.SongsRepo
import org.koin.dsl.module

fun repoMockedDBModule(dao: TrackDao) = module {
    factory { SongsRepo(get(), dao) }
}