package com.emrekose.videogames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emrekose.videogames.ui.model.FavGameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavGamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavGame(favGameItem: FavGameItem)

    @Query("DELETE FROM fav_games WHERE game_id = :gameId")
    suspend fun deleteFavGame(gameId: Int)

    @Query("SELECT * FROM fav_games")
    fun getAllFavGames(): Flow<List<FavGameItem>>

    @Query("SELECT * FROM fav_games WHERE game_id = :gameId")
    fun getFavGameById(gameId: Int): Flow<FavGameItem>
}