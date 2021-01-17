package com.emrekose.videogames.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GameDetailViewModel @ViewModelInject constructor(
    private val gameDetailUseCase: GameDetailUseCase
): ViewModel() {

    private val _gameDetailsLiveData: MutableLiveData<Resource<GameItem>> = MutableLiveData()
    val gameDetailsLiveData: LiveData<Resource<GameItem>> = _gameDetailsLiveData

    fun getGameDetails(gameId: Int) {
        gameDetailUseCase.getGameDetails(gameId)
            .onEach { _gameDetailsLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun addGameToDb(game: GameItem) {
        viewModelScope.launch {
            gameDetailUseCase.addGameToDb(game)
        }
    }

    fun deleteGameFromDb(gameId: Int) {
        viewModelScope.launch {
            gameDetailUseCase.deleteGameFromDb(gameId)
        }
    }
}