package com.emrekose.videogames.ui.home

import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.repository.GamesRepository
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val mapper: GamesMapper
){
    fun getAllGames(): Flow<Resource<List<GameItem>>> {
        return flow {
            emit(Resource.Loading)
            try {
                val games = mapper.mapFrom(gamesRepository.getAllGames())
                emit(Resource.Success(games))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}