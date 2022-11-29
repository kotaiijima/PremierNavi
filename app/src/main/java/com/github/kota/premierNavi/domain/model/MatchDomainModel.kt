package com.github.kota.premierNavi.domain.model

import com.github.kota.premierNavi.domain.TeamDomainObject


data class MatchDomainModel(
	val homeTeam: TeamInformation,
	val awayTeam: TeamInformation,
	val section: Int,
	val matchDay: String,
	val score: Score,
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