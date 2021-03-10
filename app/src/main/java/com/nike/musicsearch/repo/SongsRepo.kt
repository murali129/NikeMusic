package com.nike.musicsearch.repo

import com.nike.musicsearch.api.SongsService
import com.nike.musicsearch.persitence.TrackDB
import com.nike.musicsearch.persitence.TrackDB.Companion.mapList
import com.nike.musicsearch.persitence.TrackDao

class SongsRepo(private val songsService: SongsService, private val dao: TrackDao) {

    private val method = "track.search"
    private val apiKey = "284423812f11dcc4cd7ad66b69e8ea6e"
    
    private suspend fun searchSong(method: String, 
                                     artist: String,
                                     page: Int, 
                                     limit: Int,
                                     track: String) =
        songsService.search(method =  method, page = page.toString(), artist= artist, limit = limit, track = track, apiKey = apiKey, format = "json").await()

    public suspend fun searchSongsWithPagination(
                                            artist: String,
                                            page: Int,
                                            limit: Int,
                                            track: String): List<TrackDB> {
        
        if (track.isEmpty()) return listOf()

        val request = searchSong(method =  method, page = page, artist= artist, limit = limit, track = track)
        return mapList(trackList = request.results.trackmatches.track)
    }

    suspend fun saveSongPersistence(track: TrackDB) {
        dao.insert(track)
    }

    suspend fun getAllSongsPersistence(): List<TrackDB> {
        return dao.findAllSongs()
    }

    suspend fun deleteSongPersistence(track: TrackDB) {
        dao.delete(track)
    }
}