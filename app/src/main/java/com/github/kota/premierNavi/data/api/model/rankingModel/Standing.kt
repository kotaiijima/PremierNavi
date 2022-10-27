package com.github.kota.premierNavi.data.api.model.rankingModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Standing(
    @Json(name = "group")
    val group: String?,
    @Json(name = "stage")
    val stage: String,
    @Json(name = "table")
    val table: List<Table>,
    @Json(name = "type")
    val type: String
)