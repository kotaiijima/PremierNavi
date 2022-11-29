package com.github.kota.premierNavi.data.api.model.rankingModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiRank(
    @Json(name = "area")
    val area: Area,
    @Json(name = "competition")
    val competition: Competition,
    @Json(name = "filters")
    val filters: Filters,
    @Json(name = "season")
    val season: Season,
    @Json(name = "standings")
    val standings: List<Standing>
)