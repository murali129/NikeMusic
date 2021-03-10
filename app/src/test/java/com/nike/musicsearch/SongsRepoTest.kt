package com.nike.musicsearch

import com.nike.musicsearch.base.BaseMockServerTest
import com.nike.musicsearch.di.viewModelModule
import com.nike.musicsearch.diTest.networkMockedComponent
import com.nike.musicsearch.diTest.repoMockedDBModule
import com.nike.musicsearch.persitence.TrackDao
import com.nike.musicsearch.repo.SongsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mockito.mock
import retrofit2.HttpException
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class SongsRepoTest : BaseMockServerTest() {

    private val songsRepo by inject<SongsRepo>()
    var daoMocked = mock(TrackDao::class.java)

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    networkMockedComponent(mockServer.url("/").toString()),
                    repoMockedDBModule(daoMocked)
                )
            )
        }
    }

    @Test
    fun search_songs_result_ok() {
        mockHttpResponse("result_songs_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val songsListMocked = songsRepo.searchSongsWithPagination(page = 1, artist= "", limit = 10, track = "Believe")
            assertNotNull(songsListMocked)
            assertEquals(songsListMocked.isNullOrEmpty(), false)
        }
    }

   
    @Test(expected = HttpException::class)
    fun search_songs_result_error() {
        mockHttpResponse("result_songs_mocked.json", HttpURLConnection.HTTP_BAD_REQUEST)
        runBlocking {
            val songsListMocked = songsRepo.searchSongsWithPagination(page = 1, artist= "", limit = 10, track = "Believe")
            assertEquals(songsListMocked.isNullOrEmpty(), true)
        }
    }


}