package com.github.kota.premierNavi.data.api.model.nextGameModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Area(
    @Json(name = "code")
    val code: String,
    @Json(name = "flag")
    val flag: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)