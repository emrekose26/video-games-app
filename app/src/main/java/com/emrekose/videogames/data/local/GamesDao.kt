package com.emrekose.videogames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameItem: GameItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGames(gameList: List<GameItem>)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Transaction
    suspend fun saveGamesToLocale(gameList: List<GameItem>) {
        deleteAllGames()
        insertAllGames(gameList)
    }

    @Query("SELECT * FROM games WHERE name LIKE '%' || :query || '%'")
    fun getGameByName(query: String): Flow<List<GameItem>>

    @Query("SELECT * FROM games WHERE game_id = :gameId")
    fun getGame(gameId: Int): Flow<GameItem>

    @Query("DELETE FROM games WHERE game_id = :gameId")
    suspend fun deleteGame(gameId: Int)

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameItem>>

    @Query("SELECT * FROM games WHERE game_id = :gameId")
    fun getGameById(gameId: Int): Flow<GameItem>
}