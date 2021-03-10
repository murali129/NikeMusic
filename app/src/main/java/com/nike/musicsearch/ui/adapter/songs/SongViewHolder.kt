package com.nike.musicsearch.ui.adapter.songs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nike.musicsearch.persitence.TrackDB
import com.nike.musicsearch.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_song.view.*

class SongViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        track: TrackDB?,
        listener: SongAdapter.OnClickListener
    ) {
        track?.let {
            setupViews(it, itemView)
            setListeners(listener, track)
        }
    }

    private fun setListeners(
        listener: SongAdapter.OnClickListener,
        track: TrackDB
    ) {
        
        itemView.song_parent.setOnClickListener {
            track.url.let { url -> listener.onSongRowClicked(url) }
        }
    }


}
