package com.nike.musicsearch.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("trackmatches") val trackmatches: Trackmatches
)