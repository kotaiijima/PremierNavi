package com.github.kota.premierNavi.data.api.model.rankingModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Table(
    @Json(name = "draw")
    val draw: Int,
    @Json(name = "form")
    val form: String,
    @Json(name = "goalDifference")
    val goalDifference: Int,
    @Json(name = "goalsAgainst")
    val goalsAgainst: Int,
    @Json(name = "goalsFor")
    val goalsFor: Int,
    @Json(name = "lost")
    val lost: Int,
    @Json(name = "playedGames")
    val playedGames: Int,
    @Json(name = "points")
    val points: Int,
    @Json(name = "position")
    val position: Int,
    @Json(name = "team")
    val team: Team,
    @Json(name = "won")
    val won: Int
)