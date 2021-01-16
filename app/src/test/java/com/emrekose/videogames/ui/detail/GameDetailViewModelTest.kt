package com.emrekose.videogames.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.emrekose.videogames.common.Resource
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
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameDetailViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    
    @MockK
    private lateinit var useCase: GameDetailUseCase
    
    @InjectMockKs
    private lateinit var viewModel: GameDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given loading state, when get game details, then verify loading state`() {
        // Given
        val mockedObserver = createMockedObserver()
        viewModel.gameDetailsLiveData.observeForever(mockedObserver)
        every { useCase.getGameDetails(1) } returns flow { emit(Resource.Loading) }

        // When
        viewModel.getGameDetails(1)

        // Then
        val slot = slot<Resource<GameItem>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Truth.assertThat(slot.captured).isSameInstanceAs(Resource.Loading)

        verify { useCase.getGameDetails(1) }
    }

    @Test
    fun `given success state, when get game details, then verify data`() {
        // Given
        val mockedObserver = createMockedObserver()
        viewModel.gameDetailsLiveData.observeForever(mockedObserver)
        every { useCase.getGameDetails(1) } returns flow { emit(Resource.Success(createFakeGameItem())) }

        // When
        viewModel.getGameDetails(1)

        // Then
        val slot = slot<Resource<GameItem>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Truth.assertThat(Resource.Success(createFakeGameItem()).data.name).isEqualTo("EsEs")

        verify { useCase.getGameDetails(1) }
    }

    @Test
    fun `given error state, when get game details, then verify error state`() {
        // given
        val mockedObserver = createMockedObserver()
        viewModel.gameDetailsLiveData.observeForever(mockedObserver)

        val exception = createException()

        every { useCase.getGameDetails(1) } returns flow { emit(Resource.Error(exception)) }

        // when
        viewModel.getGameDetails(1)

        // then
        val slot = slot<Resource<GameItem>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        Truth.assertThat(slot.captured).isEqualTo(Resource.Error(exception))

        verify { useCase.getGameDetails(1) }
    }

    private fun createMockedObserver(): Observer<Resource<GameItem>> = spyk(Observer { })

    private fun createException() = spyk(Exception("This is my error message"))

    private fun createFakeGameItem() = GameItem(
        gameId = 26,
        name = "EsEs",
        backgroundImage = "eses.png",
        metacritic = 100,
        released = "19.06.1965",
        description = "Lorem ipsum dolor sit amet"
    )

}