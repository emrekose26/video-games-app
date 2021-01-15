package com.emrekose.videogames.data.local

import javax.inject.Inject

class GamesLocalDataSource @Inject constructor(
    private val gamesDao: GamesDao
) {

}