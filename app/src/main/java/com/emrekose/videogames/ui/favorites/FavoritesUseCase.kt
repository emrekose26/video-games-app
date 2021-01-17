package com.emrekose.videogames.ui.favorites

import com.emrekose.videogames.repository.GamesRepository
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    fun getAllFavGames(): Flow<List<GameItem>> = repository.getAllFavGames()

    fun getFavGameById(gameId: Int): Flow<GameItem> = repository.getFavGameById(gameId)
}