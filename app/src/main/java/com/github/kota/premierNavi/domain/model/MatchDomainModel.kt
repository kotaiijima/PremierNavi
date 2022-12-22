package com.github.kota.premierNavi.domain.model


data class MatchDomainModel(
	val homeTeam: TeamInformation,
	val awayTeam: TeamInformation,
	val section: Int,
	val matchDay: String,
	val score: Score,
	val competition: String,
	val round: String
)

data class TeamInformation(
	val id: Int,
	val name: String,
	val crest: String
)

data class Score(
	val homeScore: Int?,
	val awayScore: Int?
)