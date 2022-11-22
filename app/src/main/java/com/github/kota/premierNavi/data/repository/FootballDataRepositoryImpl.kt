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
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.handleApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FootballDataRepositoryImpl @Inject constructor(
	private val matchApi: MatchApi,
	private val teamApi: TeamApi,
	private val rankApi: RankApi,
	private val statsApi: StatsApi,
	private val preNaviDao: PreNaviDao
) : FootballDataRepository {

	override suspend fun getMatch(
		teamId: TeamIdDomainObject,
		matchStatus: String
	): ApiResult<Match> {
		return handleApi { matchApi.getMatch(teamId.value, matchStatus) }
	}

	override suspend fun getTeam(
		teamId: TeamIdDomainObject
	): ApiResult<Team> {
		return handleApi { teamApi.getTeam(teamId.value) }
	}

	override suspend fun getRank(): ApiResult<Rank> {
		return handleApi {  rankApi.getRank() }
	}

	override suspend fun getStats(
		teamId: TeamIdDomainObject
	): ApiResult<Stats> {
		return handleApi { statsApi.getStats(teamId.value) }
	}

	override fun getTeamId(): Flow<List<TeamId>> {
		return preNaviDao.getTeamId()
	}

	override suspend fun addTeamId(
		teamId: TeamId
	) {
		preNaviDao.addTeamId(teamId)
	}

	override suspend fun updateTeamId(teamId: TeamId) {
		preNaviDao.updateTeamId(teamId)
	}
}