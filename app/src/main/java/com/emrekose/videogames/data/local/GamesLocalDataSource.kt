package com.emrekose.videogames.data.local

import com.emrekose.videogames.ui.model.FavGameItem
import com.emrekose.videogames.ui.model.GameItem
import javax.inject.Inject

class GamesLocalDataSource @Inject constructor(
    private val gamesDao: GamesDao,
    private val favGamesDao: FavGamesDao
) {
    fun getAllGames() = gamesDao.getAllGames()

    fun getGameById(gameId: Int) = gamesDao.getGameById(gameId)

    suspend fun insertGame(game: GameItem) = gamesDao.insertGame(game)

    suspend fun deleteGame(gameId: Int) = gamesDao.deleteGame(gameId)

    fun getAllFavGames() = favGamesDao.getAllFavGames()

    suspend fun addFavGame(favGame: FavGameItem) = favGamesDao.addFavGame(favGame)

    suspend fun deleteFavGame(gameId: Int) = favGamesDao.deleteFavGame(gameId)

    fun getFavGameById(gameId: Int) = favGamesDao.getFavGameById(gameId)
}