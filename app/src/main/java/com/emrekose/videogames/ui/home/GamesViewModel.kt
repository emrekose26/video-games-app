package com.emrekose.videogames.ui.home

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

class GamesViewModel @ViewModelInject constructor(
    private val gamesUseCase: GamesUseCase
): ViewModel() {

    private val _gamesLiveData: MutableLiveData<Resource<List<GameItem>>> = MutableLiveData()
    val gamesLiveData: LiveData<Resource<List<GameItem>>> = _gamesLiveData

    private val _cachedGamesLiveData: MutableLiveData<List<GameItem>> = MutableLiveData()
    val cachedGamesLiveData: LiveData<List<GameItem>> = _cachedGamesLiveData

    fun getAllGames() {
        gamesUseCase.getAllGames()
            .onEach { _gamesLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun saveGamesToLocale(gameList: List<GameItem>) {
        viewModelScope.launch {
            gamesUseCase.saveGamesToLocale(gameList)
        }
    }

    fun getGameFromLocale(query: String) {
        gamesUseCase.getGameFromLocale(query)
            .onEach { _cachedGamesLiveData.value = it }
            .launchIn(viewModelScope)
    }
}