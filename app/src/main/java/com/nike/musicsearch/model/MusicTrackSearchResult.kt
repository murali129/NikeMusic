package com.nike.musicsearch.model


import com.google.gson.annotations.SerializedName

data class MusicTrackSearchResult(
    @SerializedName("results") val results: Results
)