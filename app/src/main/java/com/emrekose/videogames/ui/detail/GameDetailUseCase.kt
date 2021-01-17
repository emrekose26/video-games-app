package com.emrekose.videogames.ui.detail

import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.repository.GamesRepository
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val mapper: GameDetailMapper
){

    fun getGameDetails(gameId: Int): Flow<Resource<GameItem>> {
        return flow {
            emit(Resource.Loading)
            try {
                val gameDetails = mapper.mapFrom(gamesRepository.getGameDetails(gameId))
                emit(Resource.Success(gameDetails))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    suspend fun addGameToDb(game: GameItem) = gamesRepository.addGameToDb(game)

    suspend fun deleteGameFromDb(gameId: Int) = gamesRepository.deleteGameFromDb(gameId)
}