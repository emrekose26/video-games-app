package com.emrekose.videogames.ui.model

data class GameDetailItem(
    var gameId: Int,
    var name: String,
    var metacritic: Int,
    var released: String,
    var backgroundImage: String,
    var rating: Double,
    var description: String
)