package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.dao.PreNaviDao
import com.github.kota.premierNavi.data.databaseModel.TeamId
import com.github.kota.premierNavi.domain.repository.FootballDataRepository
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