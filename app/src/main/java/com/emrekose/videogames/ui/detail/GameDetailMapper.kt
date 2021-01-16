package com.emrekose.videogames.ui.detail

import com.emrekose.videogames.common.Mapper
import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.ui.model.GameItem
import javax.inject.Inject

class GameDetailMapper @Inject constructor(): Mapper<GameDetailResponse, GameItem> {
    override fun mapFrom(from: GameDetailResponse): GameItem {
        return GameItem(
            gameId = from.id,
            name = from.name,
            backgroundImage = from.backgroundImage,
            released = from.released,
            metacritic = from.metacritic,
            description = from.descriptionRaw
        )
    }
}