package com.github.kota.premierNavi.data.api.model.teamModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contract(
    @Json(name = "start")
    val start: String?,
    @Json(name = "until")
    val until: String?
)