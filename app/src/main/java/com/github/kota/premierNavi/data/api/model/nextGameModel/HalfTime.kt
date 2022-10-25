package com.github.kota.premierNavi.data.api.model.nextGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HalfTime(
    @Json(name = "away")
    val away: Any,
    @Json(name = "home")
    val home: Any
)