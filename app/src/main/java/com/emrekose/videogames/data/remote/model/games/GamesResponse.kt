package com.emrekose.videogames.data.remote.model.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "next")
    val next: String,
    @Json(name = "nofollow")
    val nofollow: Boolean,
    @Json(name = "nofollow_collections")
    val nofollowCollections: List<String>,
    @Json(name = "noindex")
    val noindex: Boolean,
    @Json(name = "previous")
    val previous: Any?,
    @Json(name = "results")
    val gamesResults: List<GamesResult>,
    @Json(name = "seo_description")
    val seoDescription: String,
    @Json(name = "seo_h1")
    val seoH1: String,
    @Json(name = "seo_keywords")
    val seoKeywords: String,
    @Json(name = "seo_title")
    val seoTitle: String
)