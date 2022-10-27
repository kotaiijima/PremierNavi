package com.github.kota.premierNavi.data.api.model.teamModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunningCompetition(
    @Json(name = "code")
    val code: String,
    @Json(name = "emblem")
    val emblem: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String
)