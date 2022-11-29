package com.github.kota.premierNavi.domain.model

import com.github.kota.premierNavi.domain.TeamDomainObject


data class MatchDomainModel(
	val homeTeam: TeamDomainObject,
	val awayTeam: TeamDomainObject,
	val section: Int,
	val matchDay: String,
	val score: Score,
)

data class Score(
	val homeScore: Int,
	val awayScore: Int
)