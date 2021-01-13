package com.emrekose.videogames.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.createSingleGame
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GamesDaoTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var gamesDao: GamesDao

    @Before
    fun setUp() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        gamesDao = database.gamesDao()
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `test_insert_game_to_database`() = runBlocking {
        // Given
        val game = createSingleGame()
        gamesDao.insertGame(game)

        // When
        val result = gamesDao.getGame(game.gameId)
        val expectedGame = result.first()

        // Then
        assertEquals(expectedGame, game)
    }

    @Test
    fun `test_delete_game_from_database`() = runBlocking {
        // Given
        val game = createSingleGame()
        gamesDao.insertGame(game)
        gamesDao.deleteGame(game.gameId)

        // When
        val result: List<GameItem> = gamesDao.getAllGames().first()

        // Then
        assertEquals(result.size, 0)
    }
}