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

class GamesViewModel @ViewModelInject constructor(
    private val gamesUseCase: GamesUseCase
): ViewModel() {

    private val _gamesLiveData: MutableLiveData<Resource<List<GameItem>>> = MutableLiveData()
    val gamesLiveData: LiveData<Resource<List<GameItem>>> = _gamesLiveData

    fun getAllGames() {
        gamesUseCase.getAllGames()
            .onEach { _gamesLiveData.value = it }
            .launchIn(viewModelScope)
    }
}