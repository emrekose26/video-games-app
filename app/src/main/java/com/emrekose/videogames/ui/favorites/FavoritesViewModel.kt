package com.emrekose.videogames.ui.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekose.videogames.ui.model.GameItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoritesViewModel @ViewModelInject constructor(
    private val favoritesUseCase: FavoritesUseCase
): ViewModel() {

    private val _allFavGamesLiveData: MutableLiveData<List<GameItem>> = MutableLiveData()
    val allFavGamesLiveData: LiveData<List<GameItem>> = _allFavGamesLiveData

    private val _favGameLiveData: MutableLiveData<GameItem> = MutableLiveData()
    val favGameLiveData: LiveData<GameItem> = _favGameLiveData

    fun getAllFavGames() {
        favoritesUseCase.getAllFavGames()
            .onEach { _allFavGamesLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun getFavGameById(gameId: Int) {
        favoritesUseCase.getFavGameById(gameId)
            .onEach { _favGameLiveData.value = it }
            .launchIn(viewModelScope)
    }
}