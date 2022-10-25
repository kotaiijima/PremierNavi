package com.github.kota.premierNavi.data.api.model.latestGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultSet(
    @Json(name = "competitions")
    val competitions: String,
    @Json(name = "count")
    val count: Int,
    @Json(name = "draws")
    val draws: Int,
    @Json(name = "first")
    val first: String,
    @Json(name = "last")
    val last: String,
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "played")
    val played: Int,
    @Json(name = "wins")
    val wins: Int
)