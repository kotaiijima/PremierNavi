package com.github.kota.premierNavi.data.api.model.rankingModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filters(
    @Json(name = "season")
    val season: String
)