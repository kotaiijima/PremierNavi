package com.github.kota.premierNavi.data.api.model.matchModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMatch(
    @Json(name = "filters")
    val filters: Filters,
    @Json(name = "matches")
    val matches: List<Matches>,
    @Json(name = "resultSet")
    val resultSet: ResultSet
)