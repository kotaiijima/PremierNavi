package com.github.kota.premierNavi.domain

import com.github.kota.premierNavi.data.databaseModel.TeamId
import kotlinx.coroutines.flow.Flow

interface FootballDataRepository {

  fun getTeamId(): Flow<List<TeamId>>

  suspend fun addTeamId(teamId: TeamId)

  suspend fun updateTeamId(teamId: TeamId)
}