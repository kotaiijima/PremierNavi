package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.PreNaviDao
import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.api.RankApi
import com.github.kota.premierNavi.data.api.StatsApi
import com.github.kota.premierNavi.data.api.TeamApi
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamNumberDomainObject
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.handleApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//@ViewModelScoped
class FootballDataRepositoryImpl @Inject constructor(
	private val matchApi: MatchApi,
	private val teamApi: TeamApi,
	private val rankApi: RankApi,
	private val statsApi: StatsApi,
	private val preNaviDao: PreNaviDao
) : FootballDataRepository {
	override suspend fun getMatch(
		teamNumber: TeamNumberDomainObject,
		matchStatus: String
	): ApiResult<Match> {
		return handleApi { matchApi.getMatch(teamNumber.value, matchStatus) }
	}

	override suspend fun getTeam(teamNumber: Int): ApiResult<Team> {
		return handleApi { teamApi.getTeam(teamNumber) }
	}

	override suspend fun getRank(): ApiResult<Rank> {
		return handleApi {  rankApi.getRank() }
	}

	override suspend fun getStats(teamNumber: Int): ApiResult<Stats> {
		return handleApi { statsApi.getStats(teamNumber) }
	}

	override fun getTeamId(): Flow<List<TeamId>> {
		return preNaviDao.getTeamId()
	}

	override suspend fun addTeamId(teamId: TeamId) {
		preNaviDao.addTeamId(teamId)
	}

	override suspend fun updateTeamId(teamId: TeamId) {
		preNaviDao.updateTeamId(teamId)
	}
}