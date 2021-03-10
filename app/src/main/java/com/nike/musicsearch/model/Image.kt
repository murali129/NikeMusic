package com.nike.musicsearch.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size") val size: ImageSize,
    @SerializedName("#text") val text: String
)

enum class ImageSize {
    @SerializedName("small") SMALL,
    @SerializedName("medium") MEDIUM,
    @SerializedName("large") LARGE
}