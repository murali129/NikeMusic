package com.nike.musicsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nike.musicsearch.api.NetworkState
import com.nike.musicsearch.base.BaseViewModel
import com.nike.musicsearch.persitence.TrackDB
import com.nike.musicsearch.repo.SongsRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchSongViewModel(repo: SongsRepo) : BaseViewModel() {
    
    var songs = MutableLiveData<List<TrackDB>>()
    
    val networkState = MutableLiveData<NetworkState>()
    
    val clearData = MutableLiveData<Boolean>(true)

    private var supervisorJob = SupervisorJob()
    
    private var songsRepo: SongsRepo = repo

    private var currentPage = 0

    private var currentTrackQuery = ""
    
    fun fetchNewSongs(track: String = currentTrackQuery) {
        
        if(currentTrackQuery != track) {
            currentTrackQuery = track
            currentPage = 0
            songs
            clearData.postValue(true)
        }
        
        currentPage++
        
        networkState.postValue(NetworkState.RUNNING)
        ioScope.launch(getJobErrorHandler() + supervisorJob) {
            val songResults =  songsRepo.searchSongsWithPagination( page = currentPage, artist= "", limit = 10, track = currentTrackQuery)
            networkState.postValue(NetworkState.SUCCESS)
            songs.postValue(songResults)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }
    
    
    
    
}