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
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import com.github.kota.premierNavi.utils.handleApi
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FootballDataRepository @Inject constructor(
	private val matchApi: MatchApi,
	private val teamApi: TeamApi,
	private val rankApi: RankApi,
	private val statsApi: StatsApi,
	private val preNaviDao: PreNaviDao
){
	suspend fun getMatch(teamNumber: Int, matchStatus: String): ApiResult<Match>{
		return handleApi { matchApi.getMatch(teamNumber, matchStatus) }
	}

	suspend fun getTeam(teamNumber: Int): ApiResult<Team>{
		return handleApi { teamApi.getTeam(teamNumber)}
	}

	suspend fun getRank(): ApiResult<Rank> {
		return handleApi {  rankApi.getRank() }
	}

	suspend fun getStats(teamNumber: Int): ApiResult<Stats>{
		return handleApi { statsApi.getStats(teamNumber)}
	}

	fun getTeamId(): Flow<List<TeamId>> {
		return preNaviDao.getTeamId()
	}

	suspend fun addTeamId(teamId: TeamId){
		preNaviDao.addTeamId(teamId)
	}

	suspend fun updateTeamId(teamId: TeamId){
		preNaviDao.updateTeamId(teamId)
	}
}