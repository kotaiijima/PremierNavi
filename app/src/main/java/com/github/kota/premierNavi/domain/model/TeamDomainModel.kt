package com.github.kota.premierNavi.domain.model

data class TeamDomainModel(
	val teamId: Int,
	val updateTime: String,
	val crest: String,
	val stadium: String,
	val teamName: String,
	val squad: List<TeamPlayerDomainModel>,
	val coach: CoachDomainModel
)

data class TeamPlayerDomainModel(
	val playerId: Int,
	val dateOfBirth: String,
	val playerName: String,
	val nationality: String,
	val position: String
)

data class CoachDomainModel(
	val dateOfBirth: String,
	val coachName: String,
	val nationality: String,
)