package com.nike.musicsearch

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nike.musicsearch.persitence.AppDataBase
import com.nike.musicsearch.persitence.TrackDB
import com.nike.musicsearch.persitence.TrackDao
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var trackDao: TrackDao
    private lateinit var db: AppDataBase
    private lateinit var trackDB: TrackDB
    private lateinit var trackDB2: TrackDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        trackDao = db.getTrackDao()
        trackDB = TrackDB("mbid1", "artist1", "image1", 123, "name1", "streamable1", "href1")
        trackDB2 = TrackDB("mbid2", "artist2", "iamge2", 123, "name1", "streamable1", "href2")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    

    @Test
    @Throws(Exception::class)
    fun should_Get_All_Posts() {
        runBlocking {
            trackDao.insert(trackDB)
            trackDao.insert(trackDB2)
            val songDBTest = getValue<List<TrackDB>>(trackDao.findAllSongs())
            Assert.assertEquals(songDBTest.size, 2)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Replace_Post_Post() {
        runBlocking {
            trackDao.insert(trackDB)
            trackDao.insert(trackDB)
            val songDBTest = getValue<List<TrackDB>>(trackDao.findAllSongs())
            Assert.assertEquals(songDBTest.size, 1)
        }
    }
}