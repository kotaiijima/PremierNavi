package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.PreNaviDao
import com.github.kota.premierNavi.data.model.TeamId
import com.github.kota.premierNavi.domain.FootballDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FootballDataRepositoryImpl @Inject constructor(
	private val preNaviDao: PreNaviDao
) : FootballDataRepository {
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