package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.domain.model.*
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class MatchApiServiceImpl @Inject constructor(): MatchApiService {
	override suspend fun convertMatch(apiMatch: ApiResult<ApiMatch>): RequestState<MatchDomainModel> {
		return if (apiMatch is ApiResult.ApiSuccess) {
			RequestState.Success(
				MatchDomainModel(
					homeTeam = TeamInformation(
						id = apiMatch.data.matches[0].homeTeam.id,
						name = apiMatch.data.matches[0].homeTeam.shortName,
						crest = apiMatch.data.matches[0].homeTeam.crest
					),
					awayTeam = TeamInformation(
						id = apiMatch.data.matches[0].awayTeam.id,
						name = apiMatch.data.matches[0].awayTeam.shortName,
						crest = apiMatch.data.matches[0].awayTeam.crest
					),
					section = apiMatch.data.matches[0].season.currentMatchday,
					matchDay = apiMatch.data.matches[0].utcDate,
					score = Score(
						homeScore = apiMatch.data.matches[0].score.fullTime?.home,
						awayScore = apiMatch.data.matches[0].score.fullTime?.away
					)
				)
			)
		} else {
			RequestState.Empty
		}
	}
}
