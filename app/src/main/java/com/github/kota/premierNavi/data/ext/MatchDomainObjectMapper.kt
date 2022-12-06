package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.domain.model.*

fun ApiMatch.mapToDomainObject(): MatchDomainModel{
	return MatchDomainModel(
		homeTeam = TeamInformation(
			id = this.matches[0].homeTeam.id,
			name = this.matches[0].homeTeam.shortName,
			crest = this.matches[0].homeTeam.crest
		),
		awayTeam = TeamInformation(
			id = this.matches[0].awayTeam.id,
			name = this.matches[0].awayTeam.shortName,
			crest = this.matches[0].awayTeam.crest
		),
		section = this.matches[0].matchday,
		matchDay = this.matches[0].utcDate,
		score = Score(
			homeScore = this.matches[0].score.fullTime?.home,
			awayScore = this.matches[0].score.fullTime?.away
		),
		competition = this.matches[0].competition.code,
		round = this.matches[0].stage
	)
}