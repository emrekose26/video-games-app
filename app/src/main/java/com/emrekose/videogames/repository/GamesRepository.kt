package com.emrekose.videogames.repository

import com.emrekose.videogames.data.local.GamesLocalDataSource
import com.emrekose.videogames.data.remote.GamesRemoteDataSource
import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource
) {
    suspend fun getAllGames(): GamesResponse = remoteDataSource.getAllGames()

    suspend fun getGameDetails(gameId: Int): GameDetailResponse = remoteDataSource.getGameDetails(gameId)
}