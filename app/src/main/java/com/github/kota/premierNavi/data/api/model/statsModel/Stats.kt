package com.github.kota.premierNavi.data.api.model.statsModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "filters")
    val filters: Filters,
    @Json(name = "matches")
    val matches: List<Matche>,
    @Json(name = "resultSet")
    val resultSet: ResultSet
)