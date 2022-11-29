package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.data.api.model.statsModel.Match
import com.github.kota.premierNavi.domain.model.MatchInformation
import com.github.kota.premierNavi.domain.model.Score
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamInformation
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class StatsApiServiceImpl @Inject constructor(): StatsApiService {
	override suspend fun convertStats(apiStats: ApiResult<ApiStats>): RequestState<StatsDomainModel> {
		return if (apiStats is ApiResult.ApiSuccess) {
			RequestState.Success(
				StatsDomainModel(
					listMatchInformation(apiStats.data.matches)
				)
			)
		} else {
			RequestState.Empty
		}
	}
}

private fun listMatchInformation(matches: List<Match>): List<MatchInformation> {
	val matchesInformation = mutableListOf<MatchInformation>()
	for (match in matches) {
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