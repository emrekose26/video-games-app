package com.emrekose.videogames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameItem: GameItem)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT * FROM games WHERE game_id = :gameId")
    fun getGame(gameId: Int): Flow<GameItem>

    @Query("DELETE FROM games WHERE game_id = :gameId")
    suspend fun deleteGame(gameId: Int)

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameItem>>
}