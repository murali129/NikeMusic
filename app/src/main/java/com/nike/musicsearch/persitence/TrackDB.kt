package com.nike.musicsearch.persitence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nike.musicsearch.model.Image
import com.nike.musicsearch.model.Track

@Entity(tableName = "Song")
data class TrackDB(
    @PrimaryKey val mbid: String,
    @ColumnInfo(name ="artist") val artist: String,
    @ColumnInfo(name ="image") val image: String,
    @ColumnInfo(name ="listeners") val listeners: Int,
    @ColumnInfo(name ="name") val name: String,
    @ColumnInfo(name ="streamable") val streamable: String,
    @ColumnInfo(name ="url") val url: String
) {
    companion object {
        fun map(track: Track): TrackDB {
            return TrackDB(
                mbid = track.mbid,
                artist  = track.artist,
                image = ImagesConvertor.listToJson(track.image),
                name = track.name,
                listeners = track.listeners.toInt(),
                streamable = track.streamable,
                url = track.url
            )
        }

        fun mapList(trackList: List<Track>): List<TrackDB> {
            val songPostDBList = mutableListOf<TrackDB>()
            for (song in trackList) {
                songPostDBList.add(map(song))
            }
            return songPostDBList
        }
    }
}

class ImagesConvertor {

    companion object {
        @TypeConverter
        fun listToJson(value: List<Image>?) = Gson().toJson(value)

        @TypeConverter
        fun jsonToList(value: String) = Gson().fromJson(value, Array<Image>::class.java).toList()
    }
}
