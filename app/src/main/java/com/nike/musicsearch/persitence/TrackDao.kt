package com.nike.musicsearch.persitence

import androidx.room.*
import com.nike.musicsearch.utils.Constants

@Dao
interface TrackDao {

    @Query("SELECT * FROM Song")
    suspend fun findAllSongs(): List<TrackDB>

    @Query("SELECT count(*) FROM ${Constants.TABLE_SONGS}")
    suspend fun getSongsCount(): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trackDB: TrackDB)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(tracks: ArrayList<TrackDB>)

    @Delete
    suspend fun delete(trackDB: TrackDB)

    @Query("DELETE FROM ${Constants.TABLE_SONGS}")
    suspend fun deleteAllRecipeData()

}