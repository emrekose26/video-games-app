package com.emrekose.videogames.data.remote

import com.emrekose.videogames.data.remote.model.detail.GameDetailResponse
import com.emrekose.videogames.data.remote.model.games.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("games")
    suspend fun getAllGames(): GamesResponse

    @GET("games/{id}")
    suspend fun getGameDetails(@Path("id") gameId: Int): GameDetailResponse
}