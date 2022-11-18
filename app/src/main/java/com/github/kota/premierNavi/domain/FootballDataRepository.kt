package com.github.kota.premierNavi.domain

import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface FootballDataRepository {
  suspend fun getMatch(teamNumber: TeamNumberDomainObject, matchStatus: String): ApiResult<Match>

  suspend fun getTeam(teamNumber: Int): ApiResult<Team>

  suspend fun getRank(): ApiResult<Rank>

  suspend fun getStats(teamNumber: Int): ApiResult<Stats>

  fun getTeamId(): Flow<List<TeamId>>

  suspend fun addTeamId(teamId: TeamId)

  suspend fun updateTeamId(teamId: TeamId)
}