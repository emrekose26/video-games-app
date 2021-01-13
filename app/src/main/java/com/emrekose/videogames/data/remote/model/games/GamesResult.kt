package com.emrekose.videogames.data.remote.model.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesResult(
    @Json(name = "added")
    val added: Int,
    @Json(name = "background_image")
    val backgroundImage: String,
    @Json(name = "dominant_color")
    val dominantColor: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "metacritic")
    val metacritic: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "playtime")
    val playtime: Int,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "rating_top")
    val ratingTop: Int,
    @Json(name = "ratings_count")
    val ratingsCount: Int,
    @Json(name = "released")
    val released: String,
    @Json(name = "reviews_count")
    val reviewsCount: Int,
    @Json(name = "reviews_text_count")
    val reviewsTextCount: Int,
    @Json(name = "saturated_color")
    val saturatedColor: String,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "suggestions_count")
    val suggestionsCount: Int,
    @Json(name = "tba")
    val tba: Boolean,
    @Json(name = "updated")
    val updated: String,
    @Json(name = "user_game")
    val userGame: Any
)