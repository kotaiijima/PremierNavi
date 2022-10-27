package com.github.kota.premierNavi.data.api.model.statsModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filters(
    @Json(name = "competitions")
    val competitions: String,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "permission")
    val permission: String
)