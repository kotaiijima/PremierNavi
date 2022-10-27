package com.github.kota.premierNavi.data.api.model.teamModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coach(
    @Json(name = "contract")
    val contract: Contract,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lastName")
    val lastName: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "nationality")
    val nationality: String
)