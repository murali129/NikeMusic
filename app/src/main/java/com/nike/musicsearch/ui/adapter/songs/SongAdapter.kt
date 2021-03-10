package com.nike.musicsearch.ui.adapter.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nike.musicsearch.R
import com.nike.musicsearch.api.NetworkState
import com.nike.musicsearch.persitence.TrackDB


class SongAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val songs = mutableListOf<TrackDB>()
    
    interface OnClickListener {
        fun onSongRowClicked(url: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_song -> SongViewHolder(view)
            else -> throw IllegalArgumentException(parent.context.getString(R.string.viewtype_creation_error))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_song -> (holder as SongViewHolder).bind(songs[position], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
         return  R.layout.item_song
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun clearSongs() {
        songs.clear()
        notifyDataSetChanged()
    }
    
    fun addSongs(newSongs: List<TrackDB>) {
        songs.addAll(newSongs)
        notifyDataSetChanged()
    }
    
    fun sortSongsByPopularity() {
        songs.sortByDescending { it.listeners }
        notifyDataSetChanged()
    }
}
