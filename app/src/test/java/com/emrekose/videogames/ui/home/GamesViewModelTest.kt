package com.emrekose.videogames.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
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

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GamesViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var useCase: GamesUseCase

    @InjectMockKs
    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given loading state, when get all games, then verify loading state`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Given
            val mockedObserver = createMockedObserver()
            viewModel.gamesLiveData.observeForever(mockedObserver)
            every { useCase.getAllGames() } returns flow { emit(Resource.Loading) }

            // When
            viewModel.getAllGames()

            // Then
            val slot = slot<Resource<List<GameItem>>>()
            verify { mockedObserver.onChanged(capture(slot)) }

            Truth.assertThat(slot.captured).isSameInstanceAs(Resource.Loading)

            verify { useCase.getAllGames() }
        }
    }

    @Test
    fun `given success state, when get all games, then verify data`() {
        // Given
        val mockedObserver = createMockedObserver()
        viewModel.gamesLiveData.observeForever(mockedObserver)
        every { useCase.getAllGames() } returns flow { emit(Resource.Success(createFakeGameItemList())) }

        // When
        viewModel.getAllGames()

        // Then
        val slot = slot<Resource<List<GameItem>>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Truth.assertThat(Resource.Success(createFakeGameItemList()).data[0].name).isEqualTo("Game 1")

        verify { useCase.getAllGames() }
    }

    @Test
    fun `given error state, when get all games, then verify error state`() {
        // given
        val mockedObserver = createMockedObserver()
        viewModel.gamesLiveData.observeForever(mockedObserver)

        val exception = createException()

        every { useCase.getAllGames() } returns flow { emit(Resource.Error(exception)) }

        // when
        viewModel.getAllGames()

        // then
        val slot = slot<Resource<List<GameItem>>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Truth.assertThat(slot.captured).isEqualTo(Resource.Error(exception))

        verify { useCase.getAllGames() }
    }

    private fun createMockedObserver(): Observer<Resource<List<GameItem>>> = spyk(Observer { })

    private fun createException() = spyk(Exception("This is my error message"))

    private fun createFakeGameItemList() = listOf<GameItem>(
        GameItem(1,"Game 1", "bg1.png", 90, "01.09.2012"),
        GameItem(1,"Game 2", "bg2.png", 91, "12.09.2007"),
        GameItem(1,"Game 3", "bg3.png", 55, "19.01.2019"),
        GameItem(1,"Game 4", "bg4.png", 37, "28.04.1999"),
        GameItem(1,"Game 5", "bg5.png", 73, "04.12.2009"),
    )
}