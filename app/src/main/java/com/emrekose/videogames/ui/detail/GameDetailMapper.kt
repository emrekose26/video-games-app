package com.emrekose.videogames.ui.detail

import com.emrekose.videogames.common.Mapper
import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.ui.model.GameDetailItem
import javax.inject.Inject

class GameDetailMapper @Inject constructor(): Mapper<GameDetailResponse, GameDetailItem> {
    override fun mapFrom(from: GameDetailResponse): GameDetailItem {
        return GameDetailItem(
            gameId = from.id,
            name = from.name,
            backgroundImage = from.backgroundImage,
            released = from.released,
            metacritic = from.metacritic,
            description = from.descriptionRaw,
            rating = from.rating
        )
    }
}