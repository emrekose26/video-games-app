package com.emrekose.videogames.data.remote

import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import javax.inject.Inject

class GamesRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllGames(): GamesResponse = apiService.getAllGames()

    suspend fun getGameDetails(gameId: Int): GameDetailResponse = apiService.getGameDetails(gameId)
}