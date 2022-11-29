package com.github.kota.premierNavi.domain

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface FootballDataRepository {
  suspend fun getMatch(teamId: TeamIdDomainObject, matchStatus: String): ApiResult<ApiMatch>

  suspend fun getTeam(teamId: TeamIdDomainObject): ApiResult<ApiTeam>

  suspend fun getRank(): ApiResult<ApiRank>

  suspend fun getStats(teamId: TeamIdDomainObject): ApiResult<ApiStats>

  fun getTeamId(): Flow<List<TeamId>>

  suspend fun addTeamId(teamId: TeamId)

  suspend fun updateTeamId(teamId: TeamId)
}