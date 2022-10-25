package com.github.kota.premierNavi.data.api.model.latestGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullTime(
    @Json(name = "away")
    val away: Int,
    @Json(name = "home")
    val home: Int
)