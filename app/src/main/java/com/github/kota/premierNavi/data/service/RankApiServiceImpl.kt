package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.data.api.model.rankingModel.Table
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.model.Team
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class RankApiServiceImpl @Inject constructor(): RankApiService {
	override suspend fun convertRank(apiRank: ApiResult<ApiRank>): RequestState<RankDomainModel> {
		return if (apiRank is ApiResult.ApiSuccess) {
			RequestState.Success(
				RankDomainModel(
					listTeams(apiRank.data.standings[0].table)
				)
			)
		} else {
			RequestState.Empty
		}
	}
}

private fun listTeams(table: List<Table>): List<Team> {
	val teams = mutableListOf<Team>()
	for (team in table){
		teams.add(
			Team(
				position = team.position.toString(),
				crest = team.team.crest,
				teamName = team.team.shortName,
				points = team.points.toString(),
				playedGame = team.playedGames.toString(),
				won = team.won.toString(),
				draw = team.draw.toString(),
				lost = team.lost.toString(),
				goalDifference = team.goalDifference.toString(),
				id = team.team.id
			)
		)
	}
	return teams
}