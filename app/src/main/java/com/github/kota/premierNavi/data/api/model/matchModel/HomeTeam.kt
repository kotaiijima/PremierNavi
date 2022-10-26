package com.github.kota.premierNavi.data.api.model.matchModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeTeam(
    @Json(name = "crest")
    val crest: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "shortName")
    val shortName: String,
    @Json(name = "tla")
    val tla: String
)