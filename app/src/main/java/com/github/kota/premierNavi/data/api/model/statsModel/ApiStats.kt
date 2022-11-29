package com.github.kota.premierNavi.data.api.model.statsModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiStats(
	@Json(name = "filters")
    val filters: Filters,
	@Json(name = "matches")
    val matches: List<Match>,
	@Json(name = "resultSet")
    val resultSet: ResultSet
)