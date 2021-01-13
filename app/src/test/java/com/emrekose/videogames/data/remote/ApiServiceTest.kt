package com.emrekose.videogames.data.remote

import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import com.emrekose.videogames.utils.enqueueResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch list of games test`() = runBlocking {
        // Given
        mockWebServer.enqueueResponse("list-of-games.json")

        // When
        val response = apiService.getAllGames()
        val request = mockWebServer.takeRequest()

        // Then
        Truth.assertThat(response).isInstanceOf(GamesResponse::class.java)
        Truth.assertThat(response.gamesResults.size).isEqualTo(20)
        Truth.assertThat(response.gamesResults[0].name).isEqualTo("Grand Theft Auto V")
        Truth.assertThat(request.path).isEqualTo("/games")
        Truth.assertThat(request.method).isEqualTo("GET")
    }

    @Test
    fun `fetch game detail test`() = runBlocking {
        // Given
        mockWebServer.enqueueResponse("game-details.json")

        // When
        val response = apiService.getGameDetails(3498)
        val request = mockWebServer.takeRequest()

        // Then
        Truth.assertThat(response).isInstanceOf(GameDetailResponse::class.java)
        Truth.assertThat(response.name).isEqualTo("Grand Theft Auto V")
        Truth.assertThat(response.metacritic).isEqualTo(97)
        Truth.assertThat(request.path).isEqualTo("/games/3498")
        Truth.assertThat(request.method).isEqualTo("GET")
    }
}