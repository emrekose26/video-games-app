package com.emrekose.videogames.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.ui.model.FavGameItem
import com.emrekose.videogames.ui.model.GameDetailItem
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GameDetailViewModel @ViewModelInject constructor(
    private val gameDetailUseCase: GameDetailUseCase
): ViewModel() {

    private val _gameDetailsLiveData: MutableLiveData<Resource<GameDetailItem>> = MutableLiveData()
    val gameDetailsLiveData: LiveData<Resource<GameDetailItem>> = _gameDetailsLiveData

    private val _favGameLiveData: MutableLiveData<FavGameItem> = MutableLiveData()
    val favGameLiveData: LiveData<FavGameItem> = _favGameLiveData

    fun getGameDetails(gameId: Int) {
        gameDetailUseCase.getGameDetails(gameId)
            .onEach { _gameDetailsLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun addGameToDb(gameDetail: GameDetailItem) {
        viewModelScope.launch {
            gameDetailUseCase.addFavGameToDb(gameDetail)
        }
    }

    fun deleteGameFromDb(gameId: Int) {
        viewModelScope.launch {
            gameDetailUseCase.deleteFavGameFromDb(gameId)
        }
    }

    fun getFavGameById(gameId: Int) {
        gameDetailUseCase.getFavGameById(gameId)
            .onEach { _favGameLiveData.value = it }
            .launchIn(viewModelScope)
    }
}