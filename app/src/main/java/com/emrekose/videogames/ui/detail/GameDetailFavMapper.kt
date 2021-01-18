package com.emrekose.videogames.ui.detail

import com.emrekose.videogames.common.Mapper
import com.emrekose.videogames.ui.model.FavGameItem
import com.emrekose.videogames.ui.model.GameDetailItem
import javax.inject.Inject

class GameDetailFavMapper @Inject constructor(): Mapper<GameDetailItem, FavGameItem> {
    override fun mapFrom(from: GameDetailItem): FavGameItem {
        return FavGameItem(
            gameId = from.gameId,
            name = from.name,
            backgroundImage = from.backgroundImage,
            metacritic = from.metacritic,
            released = from.released
        )
    }
}