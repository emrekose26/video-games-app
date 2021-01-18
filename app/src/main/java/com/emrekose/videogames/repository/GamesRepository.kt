package com.emrekose.videogames.repository

import com.emrekose.videogames.data.local.GamesLocalDataSource
import com.emrekose.videogames.data.remote.GamesRemoteDataSource
import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import com.emrekose.videogames.ui.model.FavGameItem
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource
) {
    suspend fun getAllGames(): GamesResponse = remoteDataSource.getAllGames()

    suspend fun getGameDetails(gameId: Int): GameDetailResponse = remoteDataSource.getGameDetails(gameId)

    suspend fun saveGameToLocale(gameList: List<GameItem>) = localDataSource.saveGamesToLocale(gameList)

    fun getGameFromLocale(query: String): Flow<List<GameItem>> = localDataSource.getGameByName(query)

    fun getAllFavGames(): Flow<List<FavGameItem>> = localDataSource.getAllFavGames()

    suspend fun addFavGameToDb(favGameItem: FavGameItem) = localDataSource.addFavGame(favGameItem)

    suspend fun deleteFavGameFromDb(gameId: Int) = localDataSource.deleteFavGame(gameId)

    fun getFavGameById(gameId: Int): Flow<FavGameItem> = localDataSource.getFavGameById(gameId)
}