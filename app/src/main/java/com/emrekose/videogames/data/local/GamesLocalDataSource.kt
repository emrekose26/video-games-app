package com.emrekose.videogames.data.local

import com.emrekose.videogames.ui.model.GameItem
import javax.inject.Inject

class GamesLocalDataSource @Inject constructor(
    private val gamesDao: GamesDao
) {
    fun getAllGames() = gamesDao.getAllGames()

    fun getGameById(gameId: Int) = gamesDao.getGameById(gameId)

    suspend fun insertGame(game: GameItem) = gamesDao.insertGame(game)

    suspend fun deleteGame(gameId: Int) = gamesDao.deleteGame(gameId)
}