package com.emrekose.videogames.ui.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "games")
data class GameItem(
    @field:PrimaryKey
    @field:ColumnInfo(name = "game_id")
    val gameId: Int,
    val name: String?,
    val backgroundImage: String?,
    val metacritic: Int?,
    val released: String,
    val description: String? = null
): Parcelable