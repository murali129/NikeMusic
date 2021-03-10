package com.nike.musicsearch.di

import com.nike.musicsearch.viewmodel.SearchSongViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchSongViewModel(get()) }
}
