package com.nike.musicsearch.ui.adapter.utils

import android.view.View
import com.nike.musicsearch.model.ImageSize
import com.nike.musicsearch.persitence.ImagesConvertor
import com.nike.musicsearch.persitence.TrackDB
import com.nike.musicsearch.utils.ImageUtils
import kotlinx.android.synthetic.main.item_song.view.*

fun setupViews(trackDB: TrackDB, itemView: View) {
    val images = ImagesConvertor.jsonToList(trackDB.image)
    images.first{it.size == ImageSize.LARGE }.let { it1 -> ImageUtils.loadImage(it1.text, itemView.song_thumb, itemView.context) }
    itemView.song_title.text = trackDB.artist
    itemView.song_listeners_count.text = trackDB.listeners.toString()
    itemView.song_name.text = trackDB.name
}
