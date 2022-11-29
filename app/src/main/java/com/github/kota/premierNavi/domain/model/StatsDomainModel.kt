package com.github.kota.premierNavi.domain.model

data class StatsDomainModel(
	val matchInformation: List<MatchInformation>
)

data class MatchInformation(
	val matchDay: String,
	val section: Int?,
	val competition: String,
	val competitionRound: String,
	val score: Score,
	val homeTeam: TeamInformation,
	val awayTeam: TeamInformation
)