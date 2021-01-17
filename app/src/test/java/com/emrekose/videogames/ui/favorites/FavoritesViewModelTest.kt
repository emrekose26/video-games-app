package com.emrekose.videogames.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoritesViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var favoritesUseCase: FavoritesUseCase

    @InjectMockKs
    private lateinit var viewModel: FavoritesViewModel

    private val gameList = listOf<GameItem>(
        GameItem(1, "Game 1", "bg1.png", 12, "12-04-2001", null),
        GameItem(2, "Game 2", "bg2.png", 56, "21-09-1999", "test desc2"),
        GameItem(3, "Game 3", "bg3.png", 34, "01-12-2912", "test desc3")
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given game list, when get all fav games, then verify the list`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Given
            val mockedObserver = createMockedObserver()
            viewModel.allFavGamesLiveData.observeForever(mockedObserver)
            every { favoritesUseCase.getAllFavGames() } returns flow { emit(gameList) }

            // When
            viewModel.getAllFavGames()

            // Then
            val slot = slot<List<GameItem>>()
            verify { mockedObserver.onChanged(capture(slot)) }

            Truth.assertThat(slot.captured.size).isEqualTo(gameList.size)

            verify { favoritesUseCase.getAllFavGames() }
        }
    }

    private fun createMockedObserver(): Observer<List<GameItem>> = spyk(Observer { })
}