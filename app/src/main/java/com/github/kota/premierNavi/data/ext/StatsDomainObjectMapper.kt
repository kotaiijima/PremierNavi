package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.data.api.model.statsModel.Match
import com.github.kota.premierNavi.domain.model.MatchInformation
import com.github.kota.premierNavi.domain.model.Score
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamInformation

fun ApiStats.mapToDomainObject(): StatsDomainModel {
	return StatsDomainModel(
		listMatchInformation(this.matches)
	)
}

private fun listMatchInformation(matches: List<Match>): List<MatchInformation> {
	val matchesInformation = mutableListOf<MatchInformation>()
	matches.forEach{ match ->
		matchesInformation.add(
			MatchInformation(
				matchDay = match.utcDate,
				section = match.matchday,
				competition = match.competition.code,
				competitionRound = match.stage,
				score = Score(
					homeScore = match.score.fullTime.home,
					awayScore = match.score.fullTime.away
				),
				homeTeam = TeamInformation(
					id = match.homeTeam.id,
					name = match.homeTeam.shortName,
					crest = match.homeTeam.crest
				),
				awayTeam = TeamInformation(
					id = match.awayTeam.id,
					name = match.awayTeam.shortName,
					crest = match.awayTeam.crest
				)
			)
		)
	}
	return matchesInformation
}
