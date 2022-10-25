package com.github.kota.premierNavi.data.api.model.latestGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Odds(
    @Json(name = "msg")
    val msg: String
)