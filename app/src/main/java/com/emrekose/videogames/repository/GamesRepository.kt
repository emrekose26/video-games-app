package com.emrekose.videogames.repository

import com.emrekose.videogames.data.local.GamesLocalDataSource
import com.emrekose.videogames.data.remote.GamesRemoteDataSource
import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource
) {
    suspend fun getAllGames(): GamesResponse = remoteDataSource.getAllGames()

    suspend fun getGameDetails(gameId: Int): GameDetailResponse = remoteDataSource.getGameDetails(gameId)

    suspend fun addGameToDb(game: GameItem) = localDataSource.insertGame(game)

    suspend fun deleteGameFromDb(gameId: Int) = localDataSource.deleteGame(gameId)

    fun getAllFavGames(): Flow<List<GameItem>> = localDataSource.getAllGames()

    fun getFavGameById(gameId: Int): Flow<GameItem> = localDataSource.getGameById(gameId)
}