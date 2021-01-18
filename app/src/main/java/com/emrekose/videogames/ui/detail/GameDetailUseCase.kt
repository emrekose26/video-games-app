package com.emrekose.videogames.ui.detail

import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.repository.GamesRepository
import com.emrekose.videogames.ui.model.GameDetailItem
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val detailMapper: GameDetailMapper,
    private val detailFavMapper: GameDetailFavMapper
){

    fun getGameDetails(gameId: Int): Flow<Resource<GameDetailItem>> {
        return flow {
            emit(Resource.Loading)
            try {
                val gameDetails = detailMapper.mapFrom(gamesRepository.getGameDetails(gameId))
                emit(Resource.Success(gameDetails))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    suspend fun addFavGameToDb(gameDetail: GameDetailItem) {
        val game = detailFavMapper.mapFrom(gameDetail)
        gamesRepository.addFavGameToDb(game)
    }

    suspend fun deleteFavGameFromDb(gameId: Int) = gamesRepository.deleteFavGameFromDb(gameId)

    fun getFavGameById(gameId: Int) = gamesRepository.getFavGameById(gameId)
}