package com.emrekose.videogames.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_games")
data class FavGameItem(
    @field:PrimaryKey
    @field:ColumnInfo(name = "game_id")
    val gameId: Int,
    val name: String?,
    val backgroundImage: String?,
    val metacritic: Int?,
    val released: String,
)