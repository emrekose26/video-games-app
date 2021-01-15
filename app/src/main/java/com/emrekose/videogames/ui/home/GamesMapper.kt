package com.emrekose.videogames.ui.home

import com.emrekose.videogames.common.Mapper
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import com.emrekose.videogames.ui.model.GameItem
import javax.inject.Inject

class GamesMapper @Inject constructor(): Mapper<GamesResponse, List<GameItem>> {
    override fun mapFrom(from: GamesResponse): List<GameItem> {
        return from.gamesResults.map { gamesResult ->
            GameItem(
                gameId = gamesResult.id,
                name = gamesResult.name,
                backgroundImage = gamesResult.backgroundImage,
                metacritic = gamesResult.metacritic,
                released = gamesResult.released
            )
        }
    }
}
