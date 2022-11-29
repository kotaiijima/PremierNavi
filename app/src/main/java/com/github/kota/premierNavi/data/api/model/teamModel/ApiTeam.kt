package com.github.kota.premierNavi.data.api.model.teamModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiTeam(
    @Json(name = "address")
    val address: String,
    @Json(name = "area")
    val area: Area,
    @Json(name = "clubColors")
    val clubColors: String,
    @Json(name = "coach")
    val coach: Coach,
    @Json(name = "crest")
    val crest: String,
    @Json(name = "founded")
    val founded: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lastUpdated")
    val lastUpdated: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "runningCompetitions")
    val runningCompetitions: List<RunningCompetition>,
    @Json(name = "shortName")
    val shortName: String,
    @Json(name = "squad")
    val squad: List<Squad>,
    @Json(name = "staff")
    val staff: List<Any>,
    @Json(name = "tla")
    val tla: String,
    @Json(name = "venue")
    val venue: String,
    @Json(name = "website")
    val website: String
)