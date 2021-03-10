package com.nike.musicsearch.api

import com.nike.musicsearch.model.MusicTrackSearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsService {

    @GET("2.0/")
    fun search(
        @Query("method") method: String,
        @Query("track") track: String,
        @Query("page") page: String,
        @Query("limit") limit: Int,
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String
    ): Deferred<MusicTrackSearchResult>
}