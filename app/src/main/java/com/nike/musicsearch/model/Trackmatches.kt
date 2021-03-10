package com.nike.musicsearch.model


import com.google.gson.annotations.SerializedName

data class Trackmatches(
    @SerializedName("track") val track: List<Track>
)