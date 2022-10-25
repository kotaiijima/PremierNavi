package com.github.kota.premierNavi.data.api.model.latestGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    @Json(name = "filters")
    val filters: Filters,
    @Json(name = "matches")
    val matches: List<Matche>,
    @Json(name = "resultSet")
    val resultSet: ResultSet
)